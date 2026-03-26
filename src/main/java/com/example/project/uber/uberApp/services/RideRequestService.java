package com.example.project.uber.uberApp.services;

import com.example.project.uber.uberApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideId);
    void update(RideRequest rideRequest);
}
