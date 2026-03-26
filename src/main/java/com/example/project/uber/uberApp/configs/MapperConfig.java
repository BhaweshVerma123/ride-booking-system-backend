package com.example.project.uber.uberApp.configs;

import com.example.project.uber.uberApp.dto.PointDto;
import com.example.project.uber.uberApp.entities.Ride;
import com.example.project.uber.uberApp.entities.RideRequest;
import com.example.project.uber.uberApp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Fix RideRequest → Ride field name mismatch (pickupLocation vs pickUpLocation)
        mapper.typeMap(RideRequest.class, Ride.class).addMappings(m -> {
            m.map(RideRequest::getPickupLocation, Ride::setPickUpLocation);
            m.map(RideRequest::getDropOffLocation, Ride::setDropOffLocation);
        });

        // DTO → Entity (PointDto → Point)
        mapper.typeMap(PointDto.class, Point.class).setConverter(context -> {
            PointDto pointDto = context.getSource();
            if (pointDto == null) return null;
            return GeometryUtil.createPoint(pointDto);
        });

        // Entity → DTO (Point → PointDto)
        mapper.typeMap(Point.class, PointDto.class).setConverter(context -> {
            Point point = context.getSource();
            if (point == null) return null;
            return new PointDto(point.getY(), point.getX()); // lat, long
        });

        return mapper;
    }
}