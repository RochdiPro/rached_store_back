package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "paiements_telecom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementTelecom {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "numero_facture", nullable = false)
    private String numeroFacture;

    @Column(name = "cin_client")
    private String cinClient;

    @Column(name = "operateur", nullable = false)
    private String operateur;

    @Column(name = "type_service", nullable = false)
    private String typeService;

    private double montant;

    private double commission = 0;

    @Column(name = "mois")
    private String mois;


    @Column(name = "date_paiement", nullable = false)
    private LocalDate datePaiement = LocalDate.now();

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}

