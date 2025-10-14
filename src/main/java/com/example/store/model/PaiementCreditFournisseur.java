package com.example.store.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "paiements_credit_fournisseur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementCreditFournisseur {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "credit_fournisseur_id", nullable = false)
    private CreditFournisseur creditFournisseur;

    private Double montant;
    private LocalDate datePaiement = LocalDate.now();
    private String modePaiement = "espece";

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
}

