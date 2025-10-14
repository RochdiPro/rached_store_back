package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
@Entity
@Table(name = "personnalisation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personnalisation {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nom_magasin", nullable = false)
    private String nomMagasin = "Rached_Word_Store";

    @Column
    private String telephone;

    @Column
    private String adresse;

    @Column
    private String logo;

    @Column(name = "couleurs_principales", nullable = false)
    private String couleursPrincipales = "#3b82f6";

    @Column(name = "format_ticket", nullable = false)
    private String formatTicket = "thermique";

    @Column(name = "valeur_point", nullable = false)
    private Double valeurPoint = 0.01;

    @Column(name = "message_ticket")
    private String messageTicket = "Merci de votre visite! À bientôt.";

    @Column(name = "message_facture")
    private String messageFacture = "Merci pour votre confiance. Pour toute question, contactez-nous.";

    @Column(name = "utiliser_prix_fixe", nullable = false)
    private Boolean utiliserPrixFixe = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt ;

    @Column(name = "updated_at")
    private LocalDate updatedAt ;
}

