package com.example.project.uber.uberApp.services.Impl;

import com.example.project.uber.uberApp.dto.DriverDto;
import com.example.project.uber.uberApp.dto.RideDto;
import com.example.project.uber.uberApp.dto.RideRequestDto;
import com.example.project.uber.uberApp.dto.RiderDto;
import com.example.project.uber.uberApp.entities.*;
import com.example.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.example.project.uber.uberApp.entities.enums.RideStatus;
import com.example.project.uber.uberApp.exception.ResourceNotFoundException;
import com.example.project.uber.uberApp.repositories.RideRequestRepository;
import com.example.project.uber.uberApp.repositories.RiderRepository;
import com.example.project.uber.uberApp.services.DriverService;
import com.example.project.uber.uberApp.services.RideService;
import com.example.project.uber.uberApp.services.RiderService;
import com.example.project.uber.uberApp.strategies.RideStrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        // Calculate fare
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        // Temporarily set drivers as empty list to avoid compilation error
        List<Driver> drivers = Collections.emptyList();

        // TODO : Send notification to all the drivers about this ride request
        log.info("Ride request {} sent to {} drivers", savedRideRequest.getId(), drivers.size());

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider does not own this ride with id: " + rideId);
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        // TODO: Implement driver rating logic
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO : implement Spring Security to get the logged-in rider
        return riderRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Rider not found with id: " + 1));
    }
}