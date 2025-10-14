package com.example.store.service;

import com.example.store.model.Personnalisation;
import com.example.store.repository.PersonnalisationRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonnalisationService {
    private final PersonnalisationRepository repository;

    public List<Personnalisation> getAll() {
        return repository.findAll();
    }

    public Personnalisation getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personnalisation non trouvée"));
    }

    public Personnalisation create(Personnalisation p) {
        p.setCreatedAt(LocalDate.now());
        p.setUpdatedAt(LocalDate.now());
        return repository.save(p);
    }

    public Personnalisation update(UUID id, Personnalisation p) {
        return repository.findById(id).map(existing -> {
            existing.setNomMagasin(p.getNomMagasin());
            existing.setTelephone(p.getTelephone());
            existing.setAdresse(p.getAdresse());
            existing.setLogo(p.getLogo());
            existing.setCouleursPrincipales(p.getCouleursPrincipales());
            existing.setFormatTicket(p.getFormatTicket());
            existing.setValeurPoint(p.getValeurPoint());
            existing.setMessageTicket(p.getMessageTicket());
            existing.setMessageFacture(p.getMessageFacture());
            existing.setUtiliserPrixFixe(p.getUtiliserPrixFixe());
            existing.setUpdatedAt(LocalDate.now());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Personnalisation non trouvée"));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}