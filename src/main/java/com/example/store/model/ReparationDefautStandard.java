package com.example.store.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "reparation_defauts_standard",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"reparation_id", "defaut_standard_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReparationDefautStandard {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "reparation_id", nullable = false)
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "defaut_standard_id", nullable = false)
    private DefautStandard defautStandard;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
