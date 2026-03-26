package com.example.project.uber.uberApp.services.Impl;

import com.example.project.uber.uberApp.dto.DriverDto;
import com.example.project.uber.uberApp.dto.RideDto;
import com.example.project.uber.uberApp.dto.RiderDto;
import com.example.project.uber.uberApp.entities.Driver;
import com.example.project.uber.uberApp.entities.Ride;
import com.example.project.uber.uberApp.entities.RideRequest;
import com.example.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.example.project.uber.uberApp.entities.enums.RideStatus;
import com.example.project.uber.uberApp.exception.ResourceNotFoundException;
import com.example.project.uber.uberApp.repositories.DriverRepository;
import com.example.project.uber.uberApp.services.DriverService;
import com.example.project.uber.uberApp.services.PaymentService;
import com.example.project.uber.uberApp.services.RideRequestService;
import com.example.project.uber.uberApp.services.RideService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;

    @Override
    public RiderDto requestRide(Long rideId) {
        return null;
    }

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("RideRequest cannot be accepted, status is " + rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();
        if (!currentDriver.getAvailable()) {
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

        Driver savedDriver = updateDriverAvailability(currentDriver, false);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();
        if (!Objects.equals(driver.getId(), ride.getDriver().getId())) {
            throw new RuntimeException("Driver cannot cancel ride as he did not accept it");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
        }

        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if (!Objects.equals(driver.getId(), ride.getDriver().getId())) {
            throw new RuntimeException("Driver cannot start ride as he did not accept it");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride status is not CONFIRMED, status is " + ride.getRideStatus());
        }

        if (!otp.equals(ride.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        paymentService.createNewPayment(savedRide);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!Objects.equals(driver.getId(), ride.getDriver().getId())) {
            throw new RuntimeException("Driver cannot end ride as he did not accept it");

        }
        if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new RuntimeException("Ride status is not ONGOING hence cannot be ended, status is " + ride.getRideStatus());
        }

        ride.setEndedAt(LocalDateTime.now());
        Ride saveRide=rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(ride);
        return modelMapper.map(saveRide, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto rateRide(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver, pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(3L)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + 2));
    }
}