package com.example.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReparationDTO {


    private String id_client;
    private String defaut;
    private String description;
    private Double prix = 0.0;
    private String statut;
    private String codeReparation;
    private String imageUrl;

}
