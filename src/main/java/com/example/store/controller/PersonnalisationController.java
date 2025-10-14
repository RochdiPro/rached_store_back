package com.example.store.controller;

import com.example.store.model.Personnalisation;
import com.example.store.service.PersonnalisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personnalisation")
@RequiredArgsConstructor
public class PersonnalisationController {

    private final PersonnalisationService service;

    @GetMapping
    public List<Personnalisation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Personnalisation getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public Personnalisation create(@RequestBody Personnalisation p) {
        return service.create(p);
    }

    @PutMapping("/{id}")
    public Personnalisation update(@PathVariable UUID id, @RequestBody Personnalisation p) {
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
