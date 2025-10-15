package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "paiements_credit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementClient  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private CreditClient credit;

    private double montant;
    private String modePaiement; // espece / carte / autre
    private LocalDate datePaiement;

    private String id_credit;
}
