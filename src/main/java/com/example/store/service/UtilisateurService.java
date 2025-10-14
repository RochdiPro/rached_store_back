package com.example.store.service;

import com.example.store.model.Utilisateur;
import com.example.store.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository repository;

    public List<Utilisateur> getAll() {
        return repository.findAll();
    }

    public Utilisateur getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur create(Utilisateur utilisateur) {
        if (repository.existsByLogin(utilisateur.getLogin())) {
            throw new RuntimeException("Login déjà utilisé");
        }
        return repository.save(utilisateur);
    }

    public Utilisateur update(UUID id, Utilisateur utilisateur) {
        Utilisateur existing = getById(id);
        existing.setNom(utilisateur.getNom());
        existing.setLogin(utilisateur.getLogin());
        existing.setRole(utilisateur.getRole());
        existing.setActif(utilisateur.getActif());
        existing.setGestionUtilisateur(utilisateur.getGestionUtilisateur());
        existing.setGestionProduit(utilisateur.getGestionProduit());
        existing.setGestionAchat(utilisateur.getGestionAchat());
        existing.setGestionVente(utilisateur.getGestionVente());
        existing.setGestionClient(utilisateur.getGestionClient());
        existing.setGestionFournisseur(utilisateur.getGestionFournisseur());
        existing.setGestionCredit(utilisateur.getGestionCredit());
        existing.setGestionReparation(utilisateur.getGestionReparation());
        existing.setGestionSession(utilisateur.getGestionSession());
        existing.setGestionDashboard(utilisateur.getGestionDashboard());
        existing.setGestionAnalyse(utilisateur.getGestionAnalyse());
        existing.setGestionPersonnalisation(utilisateur.getGestionPersonnalisation());
        existing.setGestionTelecom(utilisateur.getGestionTelecom());
        existing.setUpdatedAt(java.time.Instant.now());
        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
