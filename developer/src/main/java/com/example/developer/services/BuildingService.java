package com.example.developer.services;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import com.example.developer.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    private final BuildingRepository buildingRepository;

    public List<Building> findAll(boolean sortedByTitle) {
        if (sortedByTitle) {
            return buildingRepository.findAll(Sort.by("title"));
        }
        else {
            return buildingRepository.findAll();
        }
    }

    public Building findById(int id) {
        return buildingRepository.findById(id).orElse(null);
    }

    public List<Building> findByTitleStartsWith(String prefix) {
        return buildingRepository.findByTitleStartsWith(prefix);
    }

    public List<Building> findBetweenDistance(int min, int max) {
        return buildingRepository.findBuildingsByDistanceBetween(min, max);
    }
    public List<Apartment> getApartmentsByBuildingTitle(String title) throws Exception {
        Building building = buildingRepository.findBuildingByTitle(title);
        if (building != null) {
            return building.getApartments();
        }
        else {
            throw new Exception("Нет жилого комплекса с таким названием");
        }
    }
}
