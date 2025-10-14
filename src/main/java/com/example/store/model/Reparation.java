package com.example.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reparations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reparation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private String defaut;

    @Column
    private String description;

    @Column(nullable = false)
    private Double prix = 0.0;

    @Column(name = "date_reparation", nullable = false)
    private OffsetDateTime dateReparation = OffsetDateTime.now();

    @Column(nullable = false)
    private String statut = "en_attente";

    @Column(name = "code_reparation", unique = true)
    private String codeReparation;

    @ManyToOne
    @JoinColumn(name = "defaut_standard_id") // pour migration éventuelle
    private DefautStandard defautStandard;

    @OneToMany(mappedBy = "reparation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReparationPiece> pieces;

    @OneToMany(mappedBy = "reparation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReparationDefautStandard> defautsStandard; // nouvelle relation

    @Column(name = "image_url")
    private String imageUrl;

    private LocalDate createdAt ;

    private LocalDate updatedAt ;
}
