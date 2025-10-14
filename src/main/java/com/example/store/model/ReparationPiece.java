package com.example.store.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "reparation_pieces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReparationPiece {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "reparation_id", nullable = false)
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private Integer quantite = 1;

    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaire = 0.0;

    @Column(nullable = false)
    private Double total = 0.0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
