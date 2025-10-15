package com.example.store.dto;

import com.example.store.model.Client;
import com.example.store.model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VenteDTO {

    private UUID id;
    private ClientDTO client;
   // private Utilisateur utilisateur;
    private LocalDate date = LocalDate.now();
    private String modePaiement ;
    private Double totalHt = 0.0;
    private Double totalTtc = 0.0;
    private String facturePdf;
    private String etiquetteA8;
    private LocalDate createdAt ;
    private LocalDate updatedAt ;
     private List<ProduitVenduDTO> produits ;
}
