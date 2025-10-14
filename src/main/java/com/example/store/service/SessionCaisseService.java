package com.example.store.service;

import com.example.store.model.SessionCaisse;
import com.example.store.repository.SessionCaisseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SessionCaisseService {

    private final SessionCaisseRepository repository;

    public SessionCaisseService(SessionCaisseRepository repository) {
        this.repository = repository;
    }

    public List<SessionCaisse> getAll() {
        return repository.findAll();
    }

    public SessionCaisse getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Session non trouvée"));
    }

    public SessionCaisse create(SessionCaisse session) {
        return repository.save(session);
    }

    public SessionCaisse update(UUID id, SessionCaisse session) {
        SessionCaisse existing = getById(id);
        existing.setUtilisateur(session.getUtilisateur());
        existing.setDateOuverture(session.getDateOuverture());
        existing.setDateFermeture(session.getDateFermeture());
        existing.setTotalVentesHt(session.getTotalVentesHt());
        existing.setTotalVentesTtc(session.getTotalVentesTtc());
        existing.setStatut(session.getStatut());
        existing.setUpdatedAt(session.getUpdatedAt());
        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
