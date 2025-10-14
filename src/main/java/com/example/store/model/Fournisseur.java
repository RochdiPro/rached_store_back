package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "fournisseurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fournisseur {

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
    private  double credit ;
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();
}
