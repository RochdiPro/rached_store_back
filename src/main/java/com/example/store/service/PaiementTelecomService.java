package com.example.store.service;

import com.example.store.model.PaiementTelecom;
import com.example.store.repository.PaiementTelecomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaiementTelecomService {

    private final PaiementTelecomRepository repository;

    public PaiementTelecomService(PaiementTelecomRepository repository) {
        this.repository = repository;
    }

    public List<PaiementTelecom> getAll() {
        return repository.findAll();
    }

    public PaiementTelecom getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public PaiementTelecom create(PaiementTelecom paiement) {
        return repository.save(paiement);
    }

    public PaiementTelecom update(UUID id, PaiementTelecom paiement) {
        paiement.setId(id);
        return repository.save(paiement);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
