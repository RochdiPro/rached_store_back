package com.example.store.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProduitVenduDTO {

    private UUID id;
    private VenteDTO vente;
    private ProduitDTO produit;
    private Integer quantite = 1;
    private Double prixUnitaire = 0.0;
    private Double tva = 0.0;
    private Double totalLigne = 0.0;

}
