package com.example.project.uber.uberApp.controllers;

import com.example.project.uber.uberApp.dto.RideDto;
import com.example.project.uber.uberApp.dto.RideStartDto;
import com.example.project.uber.uberApp.services.DriverService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;


    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));

    }



    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable long rideRequestId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));

    }


}
