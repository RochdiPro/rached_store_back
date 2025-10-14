package com.example.store.service;

import com.example.store.model.ProduitVendu;
import com.example.store.model.Vente;
import com.example.store.repository.ProduitVenduRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProduitVenduService {

    private final ProduitVenduRepository produitVenduRepository;

    public ProduitVenduService(ProduitVenduRepository produitVenduRepository) {
        this.produitVenduRepository = produitVenduRepository;
    }

    public List<ProduitVendu> getProduitsVendusByVente(Vente vente) {
        return produitVenduRepository.findByVente(vente);
    }

    public ProduitVendu createProduitVendu(ProduitVendu produitVendu) {
        // Calcul automatique du total de ligne si besoin
        double total = produitVendu.getQuantite() * produitVendu.getPrixUnitaire();
        produitVendu.setTotalLigne(total);
        return produitVenduRepository.save(produitVendu);
    }

    public void deleteProduitVendu(UUID id) {
        produitVenduRepository.deleteById(id);
    }
}
