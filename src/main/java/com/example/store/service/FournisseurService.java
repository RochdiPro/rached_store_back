package com.example.store.service;

import com.example.store.model.Fournisseur;
import com.example.store.repository.FournisseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Fournisseur> getFournisseurById(UUID id) {
        return fournisseurRepository.findById(id);
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public Fournisseur updateFournisseur(UUID id, Fournisseur updated) {
        return fournisseurRepository.findById(id).map(f -> {
            f.setNom(updated.getNom());
            f.setPrenom(updated.getPrenom());
            f.setType(updated.getType());
            f.setRaisonSociale(updated.getRaisonSociale());
            f.setCin(updated.getCin());
            f.setNumFiscal(updated.getNumFiscal());
            f.setTel(updated.getTel());
            f.setEmail(updated.getEmail());
            f.setAdresse(updated.getAdresse());
            f.setUpdatedAt(updated.getUpdatedAt());
            f.setCredit(updated.getCredit());
            return fournisseurRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Fournisseur not found"));
    }

    public void deleteFournisseur(UUID id) {
        fournisseurRepository.deleteById(id);
    }
}
