package com.example.store.controller;

import com.example.store.model.SessionCaisse;
import com.example.store.service.SessionCaisseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "*")
public class SessionCaisseController {

    private final SessionCaisseService service;

    public SessionCaisseController(SessionCaisseService service) {
        this.service = service;
    }

    @GetMapping
    public List<SessionCaisse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SessionCaisse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public SessionCaisse create(@RequestBody SessionCaisse session) {
        return service.create(session);
    }

    @PutMapping("/{id}")
    public SessionCaisse update(@PathVariable UUID id, @RequestBody SessionCaisse session) {
        return service.update(id, session);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
