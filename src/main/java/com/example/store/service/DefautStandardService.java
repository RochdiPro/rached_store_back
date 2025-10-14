package com.example.store.service;

import com.example.store.model.DefautStandard;
import com.example.store.repository.DefautStandardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefautStandardService {

    private final DefautStandardRepository repository;

    public DefautStandardService(DefautStandardRepository repository) {
        this.repository = repository;
    }

    public List<DefautStandard> getAll() {
        return repository.findAll();
    }

    public List<DefautStandard> getActifs() {
        return repository.findByActifTrue();
    }

    public DefautStandard getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Défaut non trouvé"));
    }

    public DefautStandard create(DefautStandard defaut) {
        return repository.save(defaut);
    }

    public DefautStandard update(UUID id, DefautStandard defaut) {
        DefautStandard existant = getById(id);
        existant.setNom(defaut.getNom());
        existant.setCategorie(defaut.getCategorie());
        existant.setPrix(defaut.getPrix());
        existant.setActif(defaut.getActif());
        existant.setDescription(defaut.getDescription());
        return repository.save(existant);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
