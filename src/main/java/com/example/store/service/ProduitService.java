package com.example.store.service;

import com.example.store.dto.ProduitDTO;
import com.example.store.dto.ProduitDetailDTO;
import com.example.store.model.Produit;
import com.example.store.model.ProduitDetail;
import com.example.store.repository.ProduitDetailRepository;
import com.example.store.repository.ProduitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitDetailRepository produitDetailRepository;

    public List<ProduitDTO> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();

        return produits.stream().map(produit -> {
            ProduitDTO dto = new ProduitDTO();
            dto.setId(produit.getId());
            dto.setNom(produit.getNom());
            dto.setMarque(produit.getMarque());
            dto.setCategorie(produit.getCategorie());
            dto.setCodeBarre(produit.getCodeBarre());
            dto.setPrixAchat(produit.getPrixAchat());
            dto.setPrixVente(produit.getPrixVente());
            dto.setTva(produit.getTva());
            dto.setStockActuel(produit.getStockActuel());
            dto.setStockCritique(produit.getStockCritique());
            dto.setStockAlerte(produit.getStockAlerte());
            dto.setNote(produit.getNote());
            dto.setDescription(produit.getDescription());

            dto.setImageUrl(produit.getImageUrl());

            // Conversion des détails
            if (produit.getDetails() != null) {
                List<ProduitDetailDTO> detailsDTO = produit.getDetails().stream()
                        .filter(detail -> !"VENDU".equalsIgnoreCase(detail.getEtat()) && detail.getClientId() == null)
                        .map(detail -> {
                            ProduitDetailDTO d = new ProduitDetailDTO();
                            d.setImei1(detail.getImei1());
                            d.setImei2(detail.getImei2());
                            d.setNumSerie(detail.getNumSerie());
                            d.setEtat(detail.getEtat());
                            d.setCouleur(detail.getCouleur());
                            return d;
                        })
                        .collect(Collectors.toList());

                dto.setDetails(detailsDTO);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<Produit> getProduitById(UUID id) {
        return produitRepository.findById(id);
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(UUID id, Produit updatedProduit) {
        return produitRepository.findById(id).map(produit -> {
            produit.setNom(updatedProduit.getNom());
            produit.setMarque(updatedProduit.getMarque());
            produit.setCategorie(updatedProduit.getCategorie());
            produit.setCodeBarre(updatedProduit.getCodeBarre());

            produit.setPrixAchat(updatedProduit.getPrixAchat());
            produit.setPrixVente(updatedProduit.getPrixVente());
            produit.setTva(updatedProduit.getTva());
            produit.setStockActuel(updatedProduit.getStockActuel());
            produit.setStockCritique(updatedProduit.getStockCritique());
            produit.setStockAlerte(updatedProduit.getStockAlerte());
            produit.setNote(updatedProduit.getNote());
            produit.setDescription(updatedProduit.getDescription());

            produit.setImageUrl(updatedProduit.getImageUrl());
            produit.setUpdatedAt(updatedProduit.getUpdatedAt());
            return produitRepository.save(produit);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    public void deleteProduit(UUID id) {
        produitRepository.deleteById(id);
    }


    public List<Produit> searchProduits(String term) {
        return produitRepository.findByNomContainingIgnoreCaseOrMarqueContainingIgnoreCaseOrCodeBarreContainingIgnoreCase(term, term, term);
    }

    public List<Produit> getStocksCritiques() {
        return produitRepository.findAll().stream()
                .filter(p -> p.getStockActuel() != null
                        && p.getStockCritique() != null
                        && p.getStockActuel() <= p.getStockCritique())
                .toList();
    }

    public Produit updateStock(UUID id, Integer nouveauStock) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        produit.setStockActuel(nouveauStock);
        return produitRepository.save(produit);
    }

    public Produit venteStock(UUID id, Integer qte) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        produit.setStockActuel(produit.getStockActuel() - qte);
        return produitRepository.save(produit);
    }

    public void addDetailProduit(List<ProduitDetail> liste){
        produitDetailRepository.saveAll(liste);
    }

    @Transactional
    public void venteDetailProduit(List<ProduitDetail> liste) {
        for (ProduitDetail detail : liste) {
            List<ProduitDetail> existingList = produitDetailRepository
                    .findByImei1AndImei2AndNumSerie(
                            detail.getImei1(),
                            detail.getImei2(),
                            detail.getNumSerie()
                    );

            if (!existingList.isEmpty()) {
                // Mettre à jour tous les éléments trouvés
                for (ProduitDetail pd : existingList) {
                    pd.setClientId(detail.getClientId());
                    pd.setEtat(detail.getEtat());
                    pd.setCouleur(detail.getCouleur());
                }
                produitDetailRepository.saveAll(existingList);
            } else {
                produitDetailRepository.save(detail);
            }
        }
    }

    public Map<String, Long> getTotalProduits() {
        return Map.of("count", produitRepository.countProduits());
    }

    public Map<String, Long> getStockCritique() {
        return Map.of("count", produitRepository.countStockCritique());
    }

}
