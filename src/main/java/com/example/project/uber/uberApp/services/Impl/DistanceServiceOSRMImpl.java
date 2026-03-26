package com.example.project.uber.uberApp.services.Impl;

import com.example.project.uber.uberApp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "https://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
        try {
            String coordinates = src.getX() + "," + src.getY() + ";"
                    + dest.getX() + "," + dest.getY();

            String fullUrl = OSRM_API_BASE_URL + coordinates + "?overview=false";

            System.out.println("OSRM Full URL: " + fullUrl); // check console

            OSRMResponseDto responseDto = RestClient.builder()
                    .build()
                    .get()
                    .uri(URI.create(fullUrl)) // ✅ bypass RestClient URI templating
                    .retrieve()
                    .body(OSRMResponseDto.class);

            return responseDto.getRoutes().get(0).getDistance() / 1000.0;
        } catch (Exception e) {
            throw new RuntimeException("Error getting data from OSRM " + e.getMessage());
        }
    }
}

@Data
class OSRMResponseDto {
    private List<OSRMRoute> routes;
}

@Data
class OSRMRoute {
    private Double distance;
}