package com.example.developer.models;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationResponse {

    private String accessToken;

    public AuthenticationResponse(String jwt) {
        accessToken = jwt;
    }
}
