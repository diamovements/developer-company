package com.example.developer.controllers;

import com.example.developer.dto.ClientDTO;
import com.example.developer.dto.LoginRequest;
import com.example.developer.dto.RegistrationRequest;
import com.example.developer.models.Client;
import com.example.developer.services.ClientService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
                                   HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = ((ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes())
                    .getRequest()
                    .getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT",
                    SecurityContextHolder.getContext());

            return ResponseEntity.ok()
                    .body(Map.of(
                            "message", "Успешный вход",
                            "redirect", "/account"
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Неверные учетные данные"));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request,
                                      BindingResult bindingResult) {
        if (clientService.findByEmail(request.getEmail()) != null) {
            bindingResult.rejectValue("email", "duplicate",
                    "Пользователь с такой почтой уже зарегистрирован");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "error", "Ошибка валидации",
                            "details", errors
                    ));
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(request.getName());
        clientDTO.setEmail(request.getEmail());
        clientDTO.setPassword(request.getPassword());

        clientService.saveClient(clientDTO);

        return ResponseEntity.ok()
                .body(Map.of(
                        "message", "Регистрация успешна",
                        "redirect", "/login"
                ));
    }
}