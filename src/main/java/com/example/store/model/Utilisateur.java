package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "utilisateurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "auth_user_id")
    private UUID authUserId;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String role = "vendeur";

    private Boolean actif = true;

    private Boolean gestionUtilisateur = false;
    private Boolean gestionProduit = false;
    private Boolean gestionAchat = false;
    private Boolean gestionVente = false;
    private Boolean gestionClient = false;
    private Boolean gestionFournisseur = false;
    private Boolean gestionCredit = false;
    private Boolean gestionReparation = false;
    private Boolean gestionSession = false;
    private Boolean gestionDashboard = false;
    private Boolean gestionAnalyse = false;
    private Boolean gestionPersonnalisation = false;
    private Boolean gestionTelecom = true;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();
}
