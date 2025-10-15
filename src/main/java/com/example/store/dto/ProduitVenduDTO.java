package com.example.store.dto;


import com.example.store.model.Produit;
import com.example.store.model.Vente;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private String id_produit;
    private String id_vente;
    private String id_client ;

    private String imei;
    private String imei2;
    private String numero_serie;
    private String id_fornisseur;
    private String nom;
    private String categorie;
    private String marque ;

}
