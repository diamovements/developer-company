package com.example.developer.models;


import jakarta.persistence.*;
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
    @Size(min = 1, max = 30)
    @Column(name = "floor")
    private int floor;

    @NotEmpty
    @Size(min = 15)
    private float area;

    @NotEmpty
    @Min(1000000)
    @Column(name = "price")
    private float price;

    @ManyToOne
    private Building building_title;
}
