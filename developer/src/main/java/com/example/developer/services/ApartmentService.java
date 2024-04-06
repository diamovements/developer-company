package com.example.developer.services;

import com.example.developer.models.Apartment;
import com.example.developer.models.ApartmentFilter;
import com.example.developer.repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    private final ApartmentRepository apartmentRepository;

    public List<Apartment> findAll(boolean sortedByArea, String title) {
        if (sortedByArea) {
            return apartmentRepository.findApartmentsByBuildingTitle(Sort.by("area"), title);
        }
        else {
            return apartmentRepository.findApartmentsByBuildingTitle(Sort.unsorted(), title);
        }
    }
    public List<Apartment> findByPrice(float min, float max, boolean sortedByPrice, String title) {
        if (sortedByPrice) {
            return apartmentRepository.findApartmentsByPriceBetween(min, max, Sort.by("price"), title);
        }
        else {
            return apartmentRepository.findApartmentsByPriceBetween(min, max, Sort.unsorted(), title);
        }
    }
    public List<Apartment> findBetweenArea(float min, float max, boolean sortedByArea, String title) {
        if (sortedByArea) {
            return apartmentRepository.findApartmentByAreaBetween(min, max, Sort.by("area"), title);
        }
        else {
            return apartmentRepository.findApartmentByAreaBetween(min, max, Sort.unsorted(), title);
        }
    }
    public List<Apartment> findBetweenFloor(int min, int max, boolean sortedByFloor, String title) {
        if (sortedByFloor) {
            return apartmentRepository.findApartmentsByFloorBetween(min, max, Sort.by("floor"), title);
        }
        else {
            return apartmentRepository.findApartmentsByFloorBetween(min, max, Sort.unsorted(), title);
        }
    }
    public List<Apartment> findByFilter(ApartmentFilter apartmentFilter, String title) {
        Specification<Apartment> spec = Specification.where(null);
        if (apartmentFilter.getMinFloor() != null && apartmentFilter.getMaxFloor() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.between(root.get("floor"), apartmentFilter.getMinFloor(), apartmentFilter.getMaxFloor()));
        }
        if (apartmentFilter.getMinArea() != null && apartmentFilter.getMaxArea() != null) {
            spec = spec.and(((root, query, cb) ->
                    cb.between(root.get("area"), apartmentFilter.getMinArea(), apartmentFilter.getMaxArea())));
        }
        if (apartmentFilter.getMinRooms() != null && apartmentFilter.getMaxRooms() != null) {
            spec = spec.and(((root, query, cb) ->
                    cb.between(root.get("rooms"), apartmentFilter.getMinRooms(), apartmentFilter.getMaxRooms())));
        }
        if (apartmentFilter.getMinPrice() != null && apartmentFilter.getMaxPrice() != null) {
            spec = spec.and(((root, query, cb) ->
                    cb.between(root.get("price"), apartmentFilter.getMinPrice(), apartmentFilter.getMaxPrice())));
        }
        return apartmentRepository.findAll(spec);
    }
}
