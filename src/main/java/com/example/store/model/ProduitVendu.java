package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "produits_vendus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitVendu {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "vente_id")
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

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
}

