package com.example.project.uber.uberApp.services;

import com.example.project.uber.uberApp.dto.DriverDto;
import com.example.project.uber.uberApp.dto.RideDto;
import com.example.project.uber.uberApp.dto.RiderDto;
import com.example.project.uber.uberApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RiderDto requestRide(Long rideId);
    RideDto cancelRide(Long rideId);
    RideDto startRide(Long rideId, String otp);
    RideDto endRide(Long rideId);
    RiderDto rateRider(Long rideId, Integer rating);
    DriverDto getMyProfile();
    Page<RideDto> getAllMyRides(PageRequest pageRequest);
    DriverDto rateRide(Long rideId, Integer rating);
    RideDto acceptRide(Long rideRequestId);
    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean availability);
    Driver createNewDriver(Driver driver);
}
