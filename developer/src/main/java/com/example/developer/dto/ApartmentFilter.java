package com.example.developer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
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
