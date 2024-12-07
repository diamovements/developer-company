package com.example.developer.controllers;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import com.example.developer.dto.DistanceRange;
import com.example.developer.services.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping("")
    public List<Building> find() {
        return buildingService.findAll(true);
    }
    @GetMapping("/search")
    public List<Building> findTitleStartsWith(@RequestParam String prefix) {
        return buildingService.findByTitleStartsWith(prefix);
    }

    @PostMapping("/distance")
    public List<Building> findBetween(@RequestBody DistanceRange distanceRange) {
        int min = distanceRange.getMin();
        int max = distanceRange.getMax();
        return buildingService.findBetweenDistance(min, max);
    }
    @GetMapping("{title}/apartments")
    public List<Apartment> getAparts(@PathVariable("title") String title) throws Exception {
        return buildingService.getApartmentsByBuildingTitle(title);
    }


}