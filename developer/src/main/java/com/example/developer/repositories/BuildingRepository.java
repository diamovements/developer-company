package com.example.developer.repositories;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
    List<Building> findByTitleStartsWith(String prefix);
    List<Building> findBuildingsByDistanceBetween(int min, int max);
    @Query("SELECT b FROM Building b WHERE LOWER(b.title) = LOWER(:title)")
    Building findBuildingByTitle(@Param("title") String title);
    @NonNull
    List<Building> findAll(@NonNull Sort sorted);
}
