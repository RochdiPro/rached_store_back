package com.example.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(name = "ProduitDetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDetail {
    @Id
    @GeneratedValue
    private UUID id;
    private String imei1;
    private String imei2;
    private String numSerie;
    private UUID clientId;
    private UUID fournisseur;
    private String etat ;
    private String couleur ;
    // 🔗 Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
}
