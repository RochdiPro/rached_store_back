package com.example.store.dto;

import com.example.store.model.Achat;
import com.example.store.model.Produit;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProduitAcheteDTO {


    private UUID id;
   // private Achat achat;
    private Integer quantite = 1;
    private Double prixUnitaire = 0.0;
    private Double tva = 0.0;
    private Double totalLigne = 0.0;
    private List< ProduitDetailDTO>  details;
    private String nom;
    private String categorie;
    private String marque ;

}
