package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // DTO para registro y login
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        private String username;
        private String password;
    }
         // Metodo POST Registro
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        User newUser = authService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Usuario registrado correctamente bajo ID: " + newUser.getId());
    }


        // Metodo POST Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Optional<User> userOptional = authService.authenticate(request.getUsername(), request.getPassword());
        return userOptional.map(user -> ResponseEntity.ok("Bienvenido " + user.getUsername())).orElseGet(() -> ResponseEntity.status(401).body("Error: Credenciales inválidas."));
        }
    }
