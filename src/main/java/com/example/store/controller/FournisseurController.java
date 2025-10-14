package com.example.store.controller;

import com.example.store.model.Fournisseur;
import com.example.store.service.FournisseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs() {
        return ResponseEntity.ok(fournisseurService.getAllFournisseurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable UUID id) {
        return fournisseurService.getFournisseurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        return ResponseEntity.ok(fournisseurService.createFournisseur(fournisseur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable UUID id, @RequestBody Fournisseur fournisseur) {
        return ResponseEntity.ok(fournisseurService.updateFournisseur(id, fournisseur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable UUID id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.noContent().build();
    }
}
