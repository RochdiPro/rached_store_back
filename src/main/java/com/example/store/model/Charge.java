package com.example.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "charges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Charge {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nom;

     private Double prix;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDate date;



    @Column(name = "created_at")
    private LocalDate createdAt ;

    @Column(name = "updated_at")
    private LocalDate updatedAt ;
}

