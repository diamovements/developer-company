package com.example.developer.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int apartment_id;

    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "floor")
    private int floor;

    @NotEmpty
    @Size(min = 15)
    @Column(name = "area")
    private float area;

    @NotEmpty
    @Min(1000000)
    @Column(name = "price")
    private float price;

    @NotEmpty
    @Max(500)
    @Column(name = "number")
    private int number;

    @NotEmpty
    @Max(7)
    @Column(name = "rooms")
    private int rooms;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private Building building;
}