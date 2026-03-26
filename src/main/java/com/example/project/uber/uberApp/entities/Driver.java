package com.example.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Data
@Entity
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private Double rating;

    private Boolean available;

    private String VechileId;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point currentLocation;

}
