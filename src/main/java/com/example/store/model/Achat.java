package com.example.store.model;



import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "achats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achat {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    private String id_fournisseur;
    private LocalDate date = LocalDate.now();

    @Column(unique = true)
    private String numFacture;

    private Double totalHt = 0.0;
    private Double totalTtc = 0.0;
    private Double tva = 0.0;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitAchete> produits = new ArrayList<>();
}
