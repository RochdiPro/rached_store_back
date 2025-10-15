package com.example.store.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDTO {
    private UUID id;
    private String type = "personne_physique";
     private String nom;
    private String prenom;
    private String raisonSociale;
    private String cin;
    private String numFiscal;
    private String tel;
    private String email;
    private String adresse;
    private Integer pointsFidelite = 0;
    private Double credit = 0.0;
     private double soldeAvance ;
     private LocalDate createdAt ;
     private LocalDate updatedAt ;
}
