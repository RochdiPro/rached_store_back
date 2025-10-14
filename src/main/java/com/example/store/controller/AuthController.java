package com.example.store.controller;

import com.example.store.dto.LoginRequest;
import com.example.store.model.Utilisateur;
import com.example.store.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 🔹 Connexion utilisateur
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Utilisateur user = authService.login(request.getLogin(), request.getPassword());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Enregistrement utilisateur
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String login, @RequestParam(required = false) String password) {
        try {
            Utilisateur user = authService.register(login, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
