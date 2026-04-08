package com.example.project.uber.uberApp.repositories;

import com.example.project.uber.uberApp.entities.Driver;
import com.example.project.uber.uberApp.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ST_DISTANCE(point1, point2)
//ST_Distance(point1,10000)


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // 1️⃣ Nearest 10 drivers — within 1 km — sorted by distance
    @Query(value = """
        SELECT d.*
        FROM driver d
        WHERE d.available = true
          AND ST_DWithin(
              d.current_location::geography,
              :pickupLocation::geography,
              1000
          )
        ORDER BY ST_Distance(
              d.current_location::geography,
              :pickupLocation::geography
        )
        LIMIT 10
        """, nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickupLocation);


    // 2️⃣ Top-rated 10 drivers — within 15 km
    @Query(value = """
        SELECT d.*
        FROM driver d
        WHERE d.available = true
          AND ST_DWithin(
              d.current_location::geography,
              :pickupLocation::geography,
              15000
          )
        ORDER BY d.rating DESC
        LIMIT 10
        """, nativeQuery = true)
    List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);

    Optional<Driver> findByUser(User user);
}
