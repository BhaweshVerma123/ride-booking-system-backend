package com.example.project.uber.uberApp.strategies.Impl;

import com.example.project.uber.uberApp.entities.Driver;
import com.example.project.uber.uberApp.entities.RideRequest;
import com.example.project.uber.uberApp.repositories.DriverRepository;
import com.example.project.uber.uberApp.strategies.DriverMatchingStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}