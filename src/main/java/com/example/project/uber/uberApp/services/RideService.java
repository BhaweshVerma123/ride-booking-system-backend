package com.example.project.uber.uberApp.services;

import com.example.project.uber.uberApp.dto.RideRequestDto;
import com.example.project.uber.uberApp.entities.Driver;
import com.example.project.uber.uberApp.entities.Ride;
import com.example.project.uber.uberApp.entities.RideRequest;
import com.example.project.uber.uberApp.entities.Rider;
import com.example.project.uber.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface RideService {

    Ride getRideById(Long id);



    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);



}
