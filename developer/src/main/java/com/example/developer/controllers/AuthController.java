package com.example.developer.controllers;

import com.example.developer.models.Client;
import com.example.developer.services.ClientService;
import com.nimbusds.oauth2.sdk.client.ClientRegistrationRequest;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/registration")
public class AuthController {

    @Autowired
    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<?> addClient(@RequestBody @Valid Client clientForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(validErrors(bindingResult));
        }
        if (!clientService.save(clientForm)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("usernameError", "Пользователь с таким именем уже существует");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

    private Map<String, String> validErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(er -> errors.put(er.getField(), er.getDefaultMessage()));
        return errors;
    }


}
