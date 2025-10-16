package com.example.store.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovementStockDTO {
        private UUID id;
        private ProduitDTO produit;
        private String id_produit;
        private String typeMouvement;
        private Integer quantite;
        private Integer stockAvant;
        private Integer stockApres;
        private String motif;
        private String reference;
       // private Utilisateur utilisateur;
        private LocalDate dateMouvement;
        private LocalDate createdAt ;

}
