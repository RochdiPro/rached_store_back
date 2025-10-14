package com.example.store.controller;

import com.example.store.model.DefautStandard;
import com.example.store.service.DefautStandardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reparations/defauts-standard")
public class DefautStandardController {

    private final DefautStandardService service;

    public DefautStandardController(DefautStandardService service) {
        this.service = service;
    }

    @GetMapping
    public List<DefautStandard> getAll() {
        return service.getAll();
    }

    @GetMapping("/actifs")
    public List<DefautStandard> getActifs() {
        return service.getActifs();
    }

    @GetMapping("/{id}")
    public DefautStandard getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public DefautStandard create(@RequestBody DefautStandard defaut) {
        return service.create(defaut);
    }

    @PutMapping("/{id}")
    public DefautStandard update(@PathVariable UUID id, @RequestBody DefautStandard defaut) {
        return service.update(id, defaut);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
