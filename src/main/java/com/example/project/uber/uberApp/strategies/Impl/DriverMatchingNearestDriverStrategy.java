package com.example.project.uber.uberApp.strategies.Impl;

import com.example.project.uber.uberApp.entities.Driver;
import com.example.project.uber.uberApp.entities.RideRequest;
import com.example.project.uber.uberApp.repositories.DriverRepository;
import com.example.project.uber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        // Corrected method usage: getPickUpLocation
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}