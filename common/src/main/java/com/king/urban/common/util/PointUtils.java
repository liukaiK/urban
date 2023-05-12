package com.king.urban.common.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class PointUtils {

    private final static GeometryFactory geometryFactory = new GeometryFactory();

    public static Point createPoint(Double longitude, Double latitude) {
        return geometryFactory.createPoint(new Coordinate(latitude, longitude));
    }

}
