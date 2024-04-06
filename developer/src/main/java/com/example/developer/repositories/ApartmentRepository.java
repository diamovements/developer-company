package com.example.developer.repositories;

import com.example.developer.models.Apartment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    List<Apartment> findApartmentsByPriceBetween(float min, float max, Sort by, String title);
    List<Apartment> findApartmentsByFloorBetween(int min, int max, Sort floor, String title);
    List<Apartment> findApartmentByAreaBetween(float min, float max, Sort area, String title);
    List<Apartment> findApartmentsByBuildingTitle(Sort area, String title);
    List<Apartment> findAll(Specification<Apartment> spec);
}
