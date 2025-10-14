package com.example.store.service;

import com.example.store.model.Client;
import com.example.store.model.MouvementSoldeAvance;
import com.example.store.model.MouvementStock;
import com.example.store.model.Produit;
import com.example.store.repository.ClientRepository;
import com.example.store.repository.MouvementSoldeAvanceRepository;
import com.example.store.repository.MouvementStockRepository;
import com.example.store.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventaireService {
    private final MouvementStockRepository stockRepo;
    private final ProduitRepository produitRepo;
    private final MouvementSoldeAvanceRepository soldeRepo;
    private final ClientRepository clientRepo;

    // ---------------- Mouvement Stock ----------------
    public MouvementStock createMouvementStock(MouvementStock mvt) {
        Produit produit = produitRepo.findById(UUID.fromString(mvt.getId_produit()))
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        mvt.setProduit(produit);
        Integer stockAvant = produit.getStockActuel();
        Integer stockApres = stockAvant;

        switch (mvt.getTypeMouvement()) {
            case "sortie": stockApres -= Math.abs(mvt.getQuantite()); break;
            case "retour": stockApres += Math.abs(mvt.getQuantite()); break;
            case "correction": stockApres = mvt.getQuantite(); break;
        }

        if (stockApres < 0) throw new RuntimeException("Stock insuffisant");

        mvt.setStockAvant(stockAvant);
        mvt.setStockApres(stockApres);

        produit.setStockActuel(stockApres);
        produitRepo.save(produit);

        return stockRepo.save(mvt);
    }

    public List<MouvementStock> getMouvementsStock(UUID produitId) {
        if (produitId != null) return stockRepo.findByProduitId(produitId);
        return stockRepo.findAll();
    }

    // ---------------- Mouvement Solde ----------------
    public MouvementSoldeAvance createMouvementSoldeAvance(MouvementSoldeAvance mvt) {
        Client client = clientRepo.findById(UUID.fromString(mvt.getId_client()))
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        double soldeAvant = client.getSoldeAvance();
        double soldeApres = soldeAvant;
        mvt.setClient(client);
        switch (mvt.getTypeMouvement()) {
            case "ajout": soldeApres += mvt.getMontant(); break;
            case "utilisation": soldeApres -= mvt.getMontant(); break;
            case "correction": soldeApres = mvt.getMontant(); break;
        }

        if (soldeApres < 0) throw new RuntimeException("Solde insuffisant");

        mvt.setSoldeAvant(soldeAvant);
        mvt.setSoldeApres(soldeApres);

        client.setSoldeAvance(soldeApres);
        clientRepo.save(client);

        return soldeRepo.save(mvt);
    }

    public List<MouvementSoldeAvance> getMouvementsSoldeAvance(UUID clientId) {
        if (clientId != null) return soldeRepo.findByClientId(clientId);
        return soldeRepo.findAll();
    }
}
