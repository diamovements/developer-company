package com.example.developer.controllers;

import com.example.developer.models.ApartmentRequest;
import com.example.developer.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ApartmentRequestController {

    private final EmailService emailService;

    @PostMapping("/apartment-request")
    public ResponseEntity<String> request(@RequestBody ApartmentRequest dto) throws Exception {
        String subject = "Квартира в ЖК " + dto.getTitle() + " ждет Вас!";
        String message = "<h2>" + dto.getFirstName() + ", спешим обрадовать!</h2>"
                + "<p>Мы забронировали за Вами право на покупку квартиры в ЖК " + dto.getTitle() + "!</p>"
                + "<p>Мы свяжемся с Вами в течение суток.</p>";
        emailService.sendEmail(dto.getEmail(), subject, message, "static/images/email.jpg");
        return new ResponseEntity<>("Заявка на покупку квартиры принята", HttpStatus.OK);
    }
}