package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "mouvements_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementStock {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    private String id_produit;

    @Column(name = "type_mouvement", nullable = false)
    private String typeMouvement; // sortie, retour, correction

    @Column(nullable = false)
    private Integer quantite;

    @Column(name = "stock_avant", nullable = false)
    private Integer stockAvant;

    @Column(name = "stock_apres", nullable = false)
    private Integer stockApres;

    @Column
    private String motif;

    @Column
    private String reference;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Column(name = "date_mouvement", nullable = false)
    private LocalDate dateMouvement;

     private LocalDate createdAt ;
}

