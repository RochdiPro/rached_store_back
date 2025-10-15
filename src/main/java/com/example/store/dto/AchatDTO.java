package com.example.store.dto;

import com.example.store.model.*;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AchatDTO {
    private UUID id;
    private LocalDate date;
    private List<ProduitAcheteDTO> produitAchete;
    private double totalHt;
    private double tva;
    private double totalTtc;
    private String fournisseur;
    private UUID fournisseur_id;
    private String numFacture;
}
