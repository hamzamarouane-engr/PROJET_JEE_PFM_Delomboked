package com.vente.Vente.Controller;

import com.vente.Vente.Dto.LoginRequest;
import com.vente.Vente.Dto.LoginResponse;
import com.vente.Vente.Security.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log =
            LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());

        LoginResponse response = new LoginResponse();
        response.setUsername(request.getUsername());
        response.setToken(token);
        response.setMessage("Authentication successful");

        return ResponseEntity.ok(response);
    }
}
