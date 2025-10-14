package com.example.store.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credits_fournisseur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditFournisseur {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

    private Double montant;
    private Double montantRestant;

    private LocalDate dateOctroi = LocalDate.now();
    private LocalDate dateEcheance;

    private String statut = "en_cours";

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @OneToMany(mappedBy = "creditFournisseur", cascade = CascadeType.ALL)
    private List<PaiementCreditFournisseur> paiements;
}
