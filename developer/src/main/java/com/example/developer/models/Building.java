package com.example.developer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int building_id;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Apartment> apartments;

    @NotEmpty
    @Column(name = "location")
    private String location;

    @Column(name = "image")
    private String image;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "distance")
    private int distance;

}