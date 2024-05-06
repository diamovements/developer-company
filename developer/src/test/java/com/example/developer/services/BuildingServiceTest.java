package com.example.developer.services;
import com.example.developer.models.Building;
import com.example.developer.repositories.BuildingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuildingServiceTest {

    @Mock
    private BuildingRepository buildingRepository;

    @InjectMocks
    private BuildingService buildingService;

    @Test
    public void testFindAllSortedByTitle() {
        Building building1 = new Building();
        building1.setTitle("A");
        Building building2 = new Building();
        building2.setTitle("B");
        List<Building> expectedBuildings = Arrays.asList(building1, building2);

        when(buildingRepository.findAll(Sort.by("title"))).thenReturn(expectedBuildings);

        List<Building> actualBuildings = buildingService.findAll(true);

        assertEquals(expectedBuildings, actualBuildings);
    }

    @Test
    public void testFindAllUnsorted() {
        Building building1 = new Building();
        building1.setTitle("A");
        Building building2 = new Building();
        building2.setTitle("B");
        List<Building> expectedBuildings = Arrays.asList(building1, building2);

        when(buildingRepository.findAll(Sort.unsorted())).thenReturn(expectedBuildings);

        List<Building> actualBuildings = buildingService.findAll(false);

        assertEquals(expectedBuildings, actualBuildings);
    }

    @Test
    public void testFindById() {
        Building expectedBuilding = new Building();
        expectedBuilding.setBuilding_id(1);

        when(buildingRepository.findById(1)).thenReturn(Optional.of(expectedBuilding));

        Building actualBuilding = buildingService.findById(1);

        assertEquals(expectedBuilding, actualBuilding);
    }

}