package com.example.store.dto;

import com.example.store.model.*;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AchatDTO {
    private UUID id;
    private LocalDate date;
    private List<ProduitAcheteDTO> produits;
    private double totalHt;
    private double tva;
    private double totalTtc;
    private String fournisseur;
    private UUID fournisseur_id;
    private String numFacture;

    public AchatDTO(Achat achat) {
        this.id = achat.getId();
        this.date = achat.getDate();
        this.fournisseur = achat.getFournisseur() != null ? achat.getFournisseur().getNom() : null;
        this.fournisseur_id = achat.getFournisseur() != null ? achat.getFournisseur().getId() : null;

        // Transformation des produits achetés en DTO
        this.produits = mapProduitsToDTO(achat.getProduits());

        this.numFacture = achat.getNumFacture() != null ? achat.getNumFacture() : null;
        this.totalHt = achat.getTotalHt();
        this.tva = achat.getTva();
        this.totalTtc = achat.getTotalTtc();
    }
    private List<ProduitAcheteDTO> mapProduitsToDTO(List<ProduitAchete> produitsAchat) {
        return produitsAchat.stream()
                .map(pa -> {
                    ProduitAcheteDTO dto = new ProduitAcheteDTO();
                    dto.setId(pa.getId());
                    // Ne pas inclure l'objet Achat complet pour éviter boucle circulaire
                    // dto.setAchat(pa.getAchat());
                    dto.setProduit(mapProduitToDTO (pa.getProduit()));
                    dto.setQuantite(pa.getQuantite() != null ? pa.getQuantite() : 1);
                    dto.setPrixUnitaire(pa.getPrixUnitaire() != null ? pa.getPrixUnitaire() : 0.0);
                    dto.setTva(pa.getTva() != null ? pa.getTva() : 0.0);
                    dto.setTotalLigne((dto.getQuantite() * dto.getPrixUnitaire()) + dto.getTva());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public ProduitDTO mapProduitToDTO(Produit produit) {
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

        // Mapper les détails
        if (produit.getDetails() != null) {
            List<ProduitDetailDTO> detailsDTO = produit.getDetails().stream()
                    .map(this::mapProduitDetailToDTO) // fonction dédiée pour les détails
                    .collect(Collectors.toList());
            dto.setDetails(detailsDTO);
        }

        return dto;
    }

    private ProduitDetailDTO mapProduitDetailToDTO(ProduitDetail detail) {
        ProduitDetailDTO dto = new ProduitDetailDTO();
         dto.setImei1(detail.getImei1());
        dto.setImei2(detail.getImei2());
        dto.setNumSerie(detail.getNumSerie());
        dto.setEtat(detail.getEtat());
        dto.setCouleur(detail.getCouleur());
        return dto;
    }

}
