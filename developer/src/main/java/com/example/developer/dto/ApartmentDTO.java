package com.example.developer.dto;

import com.example.developer.models.Building;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentDTO {

    private int apartment_id;
    private int floor;
    private float area;
    private float price;
    private int number;
    private int rooms;
    private String image;
    private String title;
}