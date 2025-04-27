package com.example.developer;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import com.example.developer.repositories.ApartmentRepository;
import com.example.developer.services.ApartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApartmentServiceTest {
    @Mock
    private ApartmentRepository apartmentRepository;
    @InjectMocks
    private ApartmentService apartmentService;
    @Mock
    private Building building;
    @Test
    void findAll() {
        Apartment ap1 = new Apartment();
        ap1.setApartment_id(1);
        ap1.setNumber(10);
        ap1.setImage("static/images/Kutuzov.jpg");
        ap1.setFloor(10);
        ap1.setArea(45);
        ap1.setRooms(2);
        ap1.setPrice(1200000);
        ap1.setBuilding(building);
        List<Apartment> list = new ArrayList<>();
        list.add(ap1);
        when(apartmentRepository.findAll(Sort.by("area"))).thenReturn(list);
        List<Apartment> got = apartmentService.findAll(true);

        assertEquals(list, got);
    }
    @Test
    void findAllByTitle() {
        Apartment ap1 = new Apartment();
        ap1.setApartment_id(1);
        ap1.setNumber(10);
        ap1.setImage("static/images/Kutuzov.jpg");
        ap1.setFloor(10);
        ap1.setArea(45);
        ap1.setRooms(2);
        ap1.setPrice(1200000);
        ap1.setBuilding(building);
        List<Apartment> list = new ArrayList<>();
        list.add(ap1);
        when(apartmentRepository.findApartmentsByBuildingTitle(Sort.by("area"), building.getTitle()))
                .thenReturn(list);
        List<Apartment> got = apartmentService.findAllByTitle(true, building.getTitle());
        assertEquals(list, got);
    }
}