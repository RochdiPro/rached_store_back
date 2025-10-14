package com.example.store.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "defauts_standard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefautStandard {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nom;

    @Column
    private String description;

    @Column(nullable = false)
    private String categorie;

    @Column(nullable = false)
    private Double prix = 0.0;

    @Column(nullable = false)
    private Boolean actif = true;

    @OneToMany(mappedBy = "defautStandard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReparationDefautStandard> reparations; // nouvelle relation

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt = OffsetDateTime.now();
}
