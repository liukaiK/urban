package com.king.urban.event.entity.event;

import com.king.urban.common.util.PointUtils;
import jakarta.persistence.Embeddable;
import org.locationtech.jts.geom.Point;


@Embeddable
public class Position {

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 案件发生地址
     */
    private String address;

    private Point point;

    protected Position() {
    }

    public Position(Double longitude, Double latitude, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.point = PointUtils.createPoint(longitude, latitude);
    }


}
