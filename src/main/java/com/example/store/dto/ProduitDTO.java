package com.example.store.dto;

import com.example.store.model.ProduitDetail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProduitDTO {

    private UUID id;
    private String nom;
    private String marque;
    private String categorie;
    private String codeBarre;
    private Double prixAchat;
    private Double prixVente;
    private Double tva;
    private Integer stockActuel;
    private Integer stockCritique;
    private Integer stockAlerte;
    private String note;
    private String description;
    private String couleur;
    private String imageUrl;
    private List<ProduitDetailDTO> details;


}
