package com.example.developer.controllers;

import com.example.developer.models.Apartment;
import com.example.developer.models.ApartmentFilter;
import com.example.developer.models.DistanceRange;
import com.example.developer.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApartmentController {
    private ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("{title}/apartments")
    public List<Apartment> getAllByTitle(@PathVariable("title") String title) {
        return apartmentService.findAll(true, title);
    }
    @GetMapping("/all-apartments")
    public List<Apartment> getAll() {
        return apartmentService.findAllApartments(true);
    }

//    @PostMapping("/{title}/apartments/floor")
//    public List<Apartment> getByFloor(@PathVariable("title") String title, @RequestBody DistanceRange distanceRange) {
//        int min = distanceRange.getMin();
//        int max = distanceRange.getMax();
//        return apartmentService.findBetweenFloor(min, max, true, title);
//    }
//
//    @PostMapping("/{title}/apartments/area")
//    public List<Apartment> getByArea(@PathVariable("title") String title, @RequestBody DistanceRange distanceRange) {
//        float min = distanceRange.getMin();
//        float max = distanceRange.getMax();
//        return apartmentService.findBetweenArea(min, max, true, title);
//    }
//
//    @PostMapping("/{title}/apartments/price")
//    public List<Apartment> getByPrice(@PathVariable("title") String title, @RequestBody DistanceRange distanceRange) {
//        float min = distanceRange.getMin();
//        float max = distanceRange.getMax();
//        return apartmentService.findByPrice(min, max, true, title);
//    }
    @PostMapping("/{title}/apartments/filter")
    public List<Apartment> filtered(@PathVariable("title") String title, @RequestBody ApartmentFilter filter) {
        return apartmentService.findByFilter(filter, title);
    }
}
