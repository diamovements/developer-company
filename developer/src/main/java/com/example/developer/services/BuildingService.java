package com.example.developer.services;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import com.example.developer.repositories.BuildingRepository;
import com.example.developer.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuildingService {

   private final BuildingRepository buildingRepository;

    @Transactional
    public List<Building> findAll(boolean sortedByTitle) {
        if (sortedByTitle) {
            return buildingRepository.findAll(Sort.by("title"));
        }
        else {
            return buildingRepository.findAll(Sort.unsorted());
        }
    }

    public List<Building> findByTitleStartsWith(String prefix) {
        return buildingRepository.findByTitleStartsWith(prefix);
    }

    public List<Building> findBetweenDistance(int min, int max) {
        return buildingRepository.findBuildingsByDistanceBetween(min, max);
    }

    public List<Apartment> getApartmentsByBuildingTitle(String title) throws Exception {
        log.info("title: " + title);
        Building building = buildingRepository.findBuildingByTitle(title);
        log.info("building: " + building);
        if (building != null) {
            return building.getApartments();
        }
        else {
            log.info("Building not found");
            throw new Exception("Нет жилого комплекса с таким названием");
        }
    }
}
