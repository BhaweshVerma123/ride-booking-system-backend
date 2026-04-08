package com.example.project.uber.uberApp.entities;

import com.example.project.uber.uberApp.entities.enums.PaymentMethod;
import com.example.project.uber.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="rides",indexes={
        @Index(name="idx_ride_rider",columnList = "rider_id"),
        @Index(name="idx_ride_driver",columnList="driver_id")
})

public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point pickUpLocation;
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDate createdTime;

    @ManyToOne(fetch=FetchType.LAZY)
    private Rider rider;

    @ManyToOne(fetch=FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String otp;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;


}
