package com.example.store.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "produits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String categorie;

    @Column(nullable = false)
    private String codeBarre;

    private Double prixAchat = 0.0;
    private Double prixVente = 0.0;
    private Double tva = 0.0;
    private Integer stockActuel = 0;
    private Integer stockCritique = 5;
    private Integer stockAlerte = 10;
    private String note;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    // 🔗 Relation OneToMany
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitDetail> details;

    @OneToMany(mappedBy = "produit")
    @JsonIgnore // ⬅️ empêche Jackson de reboucler
    private List<MouvementStock> mouvements;

}

