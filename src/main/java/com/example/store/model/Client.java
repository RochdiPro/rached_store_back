package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String type = "personne_physique";

    @Column(nullable = false)
    private String nom;

    private String prenom;
    private String raisonSociale;
    private String cin;
    private String numFiscal;
    private String tel;
    private String email;
    private String adresse;

    @Column(name = "points_fidelite")
    private Integer pointsFidelite = 0;

    private Double credit = 0.0;

    @Column(name = "solde_avance", nullable = false)
    private double soldeAvance ;

    @Column(name = "created_at")
    private LocalDate createdAt ;

    @Column(name = "updated_at")
    private LocalDate updatedAt ;
}
