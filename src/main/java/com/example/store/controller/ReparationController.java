package com.example.store.controller;

import com.example.store.model.Reparation;
import com.example.store.model.ReparationDefautStandard;
import com.example.store.model.ReparationPiece;
import com.example.store.service.ReparationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reparations")
public class ReparationController {

    private final ReparationService service;

    public ReparationController(ReparationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reparation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Reparation getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public Reparation create(@RequestBody Reparation reparation) {
        return service.create(reparation);
    }

    @PutMapping("/{id}")
    public Reparation update(@PathVariable UUID id, @RequestBody Reparation reparation) {
        return service.update(id, reparation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    // Pièces
    @PostMapping("/{reparationId}/pieces")
    public ReparationPiece addPiece(@PathVariable UUID reparationId, @RequestBody ReparationPiece piece) {
        piece.setReparation(service.getById(reparationId));
        return service.addPiece(piece);
    }

    @GetMapping("/{reparationId}/pieces")
    public List<ReparationPiece> getPieces(@PathVariable UUID reparationId) {
        return service.getPiecesByReparation(reparationId);
    }

    // Défauts standards liés
    @PostMapping("/{reparationId}/defauts-standard/{defautId}")
    public ReparationDefautStandard addDefaut(@PathVariable UUID reparationId, @PathVariable UUID defautId) {
        return service.addDefautStandardToReparation(reparationId, defautId);
    }

    @GetMapping("/{reparationId}/defauts-standard")
    public List<ReparationDefautStandard> getDefauts(@PathVariable UUID reparationId) {
        return service.getDefautsByReparation(reparationId);
    }
}
