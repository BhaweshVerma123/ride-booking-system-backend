package com.example.project.uber.uberApp.dto;

import com.example.project.uber.uberApp.entities.enums.PaymentMethod;
import com.example.project.uber.uberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private Long id;


    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private PaymentMethod paymentMethod;


    private LocalDate requestedTime;


    private RiderDto rider;
    private Double fare;


    private RideRequestStatus rideRequestStatus;





}
