package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessions_caisse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionCaisse {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    private LocalDateTime dateOuverture = LocalDateTime.now();
    private LocalDateTime dateFermeture;
    private Double soldeOuverture = 0.0;
    private Double soldeFermeture = 0.0;

    private Double totalVentesHt = 0.0;
    private Double totalVentesTtc = 0.0;
    private Double totalFacture  = 0.0;

    private String statut = "ouverte";

    @Column(name = "created_at")
    private LocalDate createdAt ;

    @Column(name = "updated_at")
    private LocalDate updatedAt ;
}
