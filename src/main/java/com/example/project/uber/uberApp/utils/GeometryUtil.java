package com.example.project.uber.uberApp.utils;

import com.example.project.uber.uberApp.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    private static final GeometryFactory geometryFactory =
            new GeometryFactory(new PrecisionModel(), 4326);

    public static Point createPoint(PointDto pointDto) {

        if (pointDto == null) {
            throw new IllegalArgumentException("PointDto cannot be null");
        }

        double latitude = pointDto.getLatitude();
        double longitude = pointDto.getLongitude();

        Coordinate coordinate = new Coordinate(longitude, latitude); // X=lon, Y=lat

        Point point = geometryFactory.createPoint(coordinate);
        point.setSRID(4326);

        return point;
    }
}
