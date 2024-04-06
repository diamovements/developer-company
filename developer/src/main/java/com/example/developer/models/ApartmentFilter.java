package com.example.developer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentFilter {
    private Integer minFloor;
    private Integer maxFloor;
    private Float minArea;
    private Float maxArea;
    private Float minPrice;
    private Float maxPrice;
    private Integer minRooms;
    private Integer maxRooms;
}
