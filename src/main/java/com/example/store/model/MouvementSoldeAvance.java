package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "mouvements_solde_avance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementSoldeAvance {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "type_mouvement", nullable = false)
    private String typeMouvement; // ajout, utilisation, correction

    @Column(nullable = false)
    private double montant;

    @Column(name = "solde_avant", nullable = false)
    private double soldeAvant;

    @Column(name = "solde_apres", nullable = false)
    private double soldeApres;

    @Column
    private String motif;

    @Column
    private String reference;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Column(name = "date_mouvement", nullable = false)
    private LocalDate dateMouvement ;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt ;
}
