package com.example.store.dto;

import com.example.store.model.Produit;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProduitDetailDTO {
    private UUID id;
    private String imei1;
    private String imei2;
    private String numSerie;
    private UUID clientId;
    private UUID fournisseur;
    private String etat ;
    private String couleur ;


}
