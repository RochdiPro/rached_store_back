package com.example.store.service;

import com.example.store.model.Charge;
import com.example.store.repository.ChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChargeService {

    private final ChargeRepository chargeRepository;

    public List<Charge> getAll() {
        return chargeRepository.findAll();
    }

    public Charge getById(UUID id) {
        return chargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Charge non trouvée"));
    }

    public Charge create(Charge charge) {
        charge.setCreatedAt(LocalDate.now());
        charge.setUpdatedAt(LocalDate.now());
        return chargeRepository.save(charge);
    }

    public Charge update(UUID id, Charge chargeDetails) {
        Charge charge = getById(id);
        charge.setNom(chargeDetails.getNom());
        charge.setPrix(chargeDetails.getPrix());
        charge.setDescription(chargeDetails.getDescription());
        charge.setDate(chargeDetails.getDate());
        charge.setUpdatedAt(LocalDate.now());
        return chargeRepository.save(charge);
    }

    public void delete(UUID id) {
        Charge charge = getById(id);
        chargeRepository.delete(charge);
    }
}
