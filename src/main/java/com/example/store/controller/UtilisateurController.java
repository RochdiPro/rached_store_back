package com.example.store.controller;

import com.example.store.model.Utilisateur;
import com.example.store.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService service;

    @GetMapping
    public List<Utilisateur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return service.create(utilisateur);
    }

    @PutMapping("/{id}")
    public Utilisateur update(@PathVariable UUID id, @RequestBody Utilisateur utilisateur) {
        return service.update(id, utilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
