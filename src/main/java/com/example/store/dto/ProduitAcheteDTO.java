package com.example.store.dto;

import com.example.store.model.Achat;
import com.example.store.model.Produit;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProduitAcheteDTO {


    private UUID id;
    private Achat achat;
    private ProduitDTO produit;
    private Integer quantite = 1;
    private Double prixUnitaire = 0.0;
    private Double tva = 0.0;
    private Double totalLigne = 0.0;
}
