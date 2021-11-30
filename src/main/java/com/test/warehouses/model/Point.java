package com.test.warehouses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {

    @JsonProperty
    private double coordinateX;

    @JsonProperty
    private double coordinateY;


    public double distanceSquare(Point point) {
        double dx = point.coordinateX - this.coordinateX;
        double dy = point.coordinateY - this.coordinateY;
        return dx * dx + dy * dy;
    }
}
