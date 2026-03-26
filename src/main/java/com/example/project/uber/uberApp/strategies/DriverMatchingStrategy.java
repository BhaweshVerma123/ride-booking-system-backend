package com.example.project.uber.uberApp.strategies;

import com.example.project.uber.uberApp.entities.Driver;
import com.example.project.uber.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    // Fixed method name to plural to match usage
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}