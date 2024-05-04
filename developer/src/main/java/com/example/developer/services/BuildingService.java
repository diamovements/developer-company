package com.example.developer.services;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import com.example.developer.repositories.BuildingRepository;
import com.example.developer.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class BuildingService {
    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }
    private final BuildingRepository buildingRepository;
    @SneakyThrows
    @Transactional
    public List<Building> findAll(boolean sortedByTitle) {
        if (sortedByTitle) {
            return buildingRepository.findAll(Sort.by("title"));
        }
        else {
            return buildingRepository.findAll(Sort.unsorted());
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
//            return building.getApartments();
            return null;
        }
        else {
            throw new Exception("Нет жилого комплекса с таким названием");
        }
    }
    public void save(Building building) {
        buildingRepository.save(building);
    }
}
