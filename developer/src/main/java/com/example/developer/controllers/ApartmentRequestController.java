package com.example.developer.controllers;

import com.example.developer.dto.ApartmentRequestDTO;
import com.example.developer.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApartmentRequestController {
    private final EmailService emailService;

    @Autowired
    public ApartmentRequestController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/apartment-request")
    public ResponseEntity<String> request(@RequestBody ApartmentRequestDTO dto) {
        String subject = "Ваша заявка на покупку квартиры в ЖК " + dto.getTitle() + " принята!";
        String message = dto.getFirstName() + ", спешим обрадовать!\nМы забронировали за Вами право на покупку квартиры в ЖК " + dto.getTitle() +
                "!\nМы свяжемся с Вами в течение суток.";
        emailService.sendEmail(dto.getEmail(), subject, message);
        return new ResponseEntity<>("Заявка на покупку квартиры принята", HttpStatus.OK);
    }
}
