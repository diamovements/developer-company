package com.example.developer.repositories;

import com.example.developer.models.Apartment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    List<Apartment> findApartmentsByPriceLessThan(float price, Sort by);
    List<Apartment> findApartmentsByFloorBetween(int min, int max, Sort floor);
    List<Apartment> findApartmentByAreaBetween(float min, float max, Sort area);
}
