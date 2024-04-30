package com.example.developer.controllers;

import com.example.developer.dto.ApartmentDTO;
import com.example.developer.models.Apartment;
import com.example.developer.models.ApartmentFilter;
import com.example.developer.models.DistanceRange;
import com.example.developer.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return apartmentService.findAllByTitle(true, title);
    }

    @GetMapping("apartments")
    public List<ApartmentDTO> getAll() {
        List<Apartment> list = apartmentService.findAll(true);
        List<ApartmentDTO> dtoList = new ArrayList<>();
        for (Apartment ap : list) {
            ApartmentDTO dto = new ApartmentDTO();
            dto.setApartment_id(ap.getApartment_id());
            dto.setArea(ap.getArea());
            dto.setFloor(ap.getFloor());
            dto.setImage(ap.getImage());
            dto.setNumber(ap.getNumber());
            dto.setPrice(ap.getPrice());
            dto.setRooms(ap.getRooms());
            dto.setTitle(ap.getBuilding().getTitle());
            dtoList.add(dto);
        }
        return dtoList;
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
    @PostMapping("apartments/filter")
    public List<ApartmentDTO> filtered(@RequestBody ApartmentFilter filter) {
        List<Apartment> list = apartmentService.findByFilter(filter);
        List<ApartmentDTO> dtoList = new ArrayList<>();
        for (Apartment ap : list) {
            ApartmentDTO dto = new ApartmentDTO();
            dto.setApartment_id(ap.getApartment_id());
            dto.setArea(ap.getArea());
            dto.setFloor(ap.getFloor());
            dto.setImage(ap.getImage());
            dto.setNumber(ap.getNumber());
            dto.setPrice(ap.getPrice());
            dto.setRooms(ap.getRooms());
            dto.setTitle(ap.getBuilding().getTitle());
            dtoList.add(dto);
        }
        return dtoList;
    }
}