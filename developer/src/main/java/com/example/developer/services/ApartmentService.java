package com.example.developer.services;

import com.example.developer.models.Apartment;
import com.example.developer.repositories.ApartmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {
    private ApartmentRepository apartmentRepository;

    public List<Apartment> findAll(boolean sortedByArea) {
        if (sortedByArea) {
            return apartmentRepository.findAll(Sort.by("area"));
        }
        else {
            return apartmentRepository.findAll();
        }
    }
    public List<Apartment> findByPrice(float price, boolean sortedByPrice) {
        if (sortedByPrice) {
            return apartmentRepository.findApartmentsByPriceLessThan(price, Sort.by("price"));
        }
        else {
            return apartmentRepository.findApartmentsByPriceLessThan(price, Sort.unsorted());
        }
    }
    public List<Apartment> findBetweenArea(float min, float max, boolean sortedByArea) {
        if (sortedByArea) {
            return apartmentRepository.findApartmentByAreaBetween(min, max, Sort.by("area"));
        }
        else {
            return apartmentRepository.findApartmentByAreaBetween(min, max, Sort.unsorted());
        }
    }
    public List<Apartment> findBetweenFloor(int min, int max, boolean sortedByFloor) {
        if (sortedByFloor) {
            return apartmentRepository.findApartmentsByFloorBetween(min, max, Sort.by("floor"));
        }
        else {
            return apartmentRepository.findApartmentsByFloorBetween(min, max, Sort.unsorted());
        }
    }
}
