package com.example.store.controller;

import com.example.store.model.PaiementTelecom;
import com.example.store.service.PaiementTelecomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/telecom")
@CrossOrigin("*")
public class PaiementTelecomController {

    private final PaiementTelecomService service;

    public PaiementTelecomController(PaiementTelecomService service) {
        this.service = service;
    }

    @GetMapping
    public List<PaiementTelecom> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PaiementTelecom getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public PaiementTelecom create(@RequestBody PaiementTelecom paiement) {
        return service.create(paiement);
    }

    @PutMapping("/{id}")
    public PaiementTelecom update(@PathVariable UUID id, @RequestBody PaiementTelecom paiement) {
        return service.update(id, paiement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
