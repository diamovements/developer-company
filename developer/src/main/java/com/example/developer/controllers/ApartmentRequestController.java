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
        String subject = "Квартира в ЖК " + dto.getTitle() + " ждет Вас!";
        String message = "<h2>" + dto.getFirstName() + ", спешим обрадовать!</h2>"
                + "<p>Мы забронировали за Вами право на покупку квартиры в ЖК " + dto.getTitle() + "!</p>"
                + "<p>Мы свяжемся с Вами в течение суток.</p>";
        emailService.sendEmail(dto.getEmail(), subject, message, "static/images/email.jpg");
        return new ResponseEntity<>("Заявка на покупку квартиры принята", HttpStatus.OK);
    }
}