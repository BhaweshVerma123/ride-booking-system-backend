package com.example.project.uber.uberApp.dto;

import com.example.project.uber.uberApp.entities.enums.PaymentMethod;
import com.example.project.uber.uberApp.entities.enums.RideStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RideDto {
    private Long id;


    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDate createdTime;


    private RiderDto rider;


    private DriverDto driver;


    private RideStatus rideStatus;

    private String otp;


    private PaymentMethod paymentMethod;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
