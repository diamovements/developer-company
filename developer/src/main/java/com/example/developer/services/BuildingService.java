package com.example.developer.services;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import com.example.developer.repositories.BuildingRepository;
import com.example.developer.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

   private final BuildingRepository buildingRepository;

    @Transactional //зачем
    public List<Building> findAll(boolean sortedByTitle) {
        if (sortedByTitle) {
            return buildingRepository.findAll(Sort.by("title")); //вынести в переменную
        }
        else {
            return buildingRepository.findAll(Sort.unsorted());
        }
    }

    //если не найдено, кинуть исключение надо
    public Building findById(int id) {
        return buildingRepository.findById(id).orElse(null);
    }


    //сделать optional
    public List<Building> findByTitleStartsWith(String prefix) {
        return buildingRepository.findByTitleStartsWith(prefix);
    }

    //тоже сделать optional
    public List<Building> findBetweenDistance(int min, int max) {
        return buildingRepository.findBuildingsByDistanceBetween(min, max);
    }

    //во-первых, тут тоже нужен optional, во вторых пользовательское исключение
    public List<Apartment> getApartmentsByBuildingTitle(String title) throws Exception {
        Building building = buildingRepository.findBuildingByTitle(title);
        if (building != null) {
            return null;
        }
        else {
            throw new Exception("Нет жилого комплекса с таким названием");
        }
    }
}
