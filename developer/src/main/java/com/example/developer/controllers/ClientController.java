package com.example.developer.controllers;

import com.example.developer.models.Apartment;
import com.example.developer.models.Client;
import com.example.developer.repositories.ApartmentRepository;
import com.example.developer.repositories.ClientRepository;
import com.example.developer.services.ClientService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
    private final ClientService clientService;
    private final ApartmentRepository apartmentRepository;
    private final ClientRepository clientRepository;

    @GetMapping("/getperson")
    @JsonIgnore
    public Client getPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            return clientService.findByEmail(username);
        } else {
            return null;
        }
    }

    @PostMapping("/addfavorite")
    public ResponseEntity<?> addToFav(@RequestBody String imageSrc, Authentication authentication) {
        try {
            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                Client client = clientService.findByEmail(username);
                if (client == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
                }

                Apartment apartment = apartmentRepository.findApartmentByImage(imageSrc);
                if (apartment == null) {
                    apartment = new Apartment();
                    apartment.setImage(imageSrc);
                    apartmentRepository.save(apartment);
                }

                client.getFavoriteApartments().add(apartment);
                clientRepository.save(client);

                return ResponseEntity.ok("Apartment added to favorites");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
