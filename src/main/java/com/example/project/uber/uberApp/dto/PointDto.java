package com.example.project.uber.uberApp.dto;

import lombok.Data;

@Data
public class PointDto {
    private double latitude;
    private double longitude;

    public PointDto() {}

    public PointDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
