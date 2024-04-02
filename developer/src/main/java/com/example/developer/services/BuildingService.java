package com.example.developer.services;

import com.example.developer.models.Building;
import com.example.developer.repositories.BuildingRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    private BuildingRepository buildingRepository;

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
}
