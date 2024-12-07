package com.example.developer.services;

import com.example.developer.models.Apartment;
import com.example.developer.dto.ApartmentFilter;
import com.example.developer.repositories.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public List<Apartment> findAll(boolean sortedByArea) {
        if (sortedByArea) {
            return apartmentRepository.findAll(Sort.by("area"));
        }
        else {
            return apartmentRepository.findAll(Sort.unsorted());
        }
    }
    public List<Apartment> findAllByTitle(boolean sortedByArea, String title) {
        if (sortedByArea) {
            return apartmentRepository.findApartmentsByBuildingTitle(Sort.by("area"), title);
        }
        else {
            return apartmentRepository.findApartmentsByBuildingTitle(Sort.unsorted(), title);
        }
    }

    public List<Apartment> findByFilter(ApartmentFilter apartmentFilter) {
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
