package com.example.developer.controllers;

import com.example.developer.models.AuthenticationResponse;
import com.example.developer.models.Client;
import com.example.developer.services.JwtUserDetailService;
import com.example.developer.util.JwtUtil;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, JwtUserDetailService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final JwtUserDetailService userDetailsService;

    @PostMapping("/api/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Client client) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(client.getUsername(), client.getPassword())
            );
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(client.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }


}
