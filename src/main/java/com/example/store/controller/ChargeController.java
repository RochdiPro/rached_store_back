package com.example.store.controller;

import com.example.store.model.Charge;
import com.example.store.service.ChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/charges")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Angular dev
public class ChargeController {

    private final ChargeService chargeService;

    @GetMapping
    public List<Charge> getAll() {
        return chargeService.getAll();
    }

    @GetMapping("/{id}")
    public Charge getById(@PathVariable UUID id) {
        return chargeService.getById(id);
    }

    @PostMapping
    public Charge create(@RequestBody Charge charge) {
        return chargeService.create(charge);
    }

    @PutMapping("/{id}")
    public Charge update(@PathVariable UUID id, @RequestBody Charge charge) {
        return chargeService.update(id, charge);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        chargeService.delete(id);
    }
}
