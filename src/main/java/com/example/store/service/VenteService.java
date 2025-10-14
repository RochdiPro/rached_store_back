package com.example.store.service;

import com.example.store.dto.ProduitDTO;
import com.example.store.dto.ProduitVenduDTO;
import com.example.store.dto.VenteDTO;
import com.example.store.model.*;
import com.example.store.repository.ClientRepository;
import com.example.store.repository.ProduitVenduRepository;
import com.example.store.repository.VenteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VenteService {

    private final VenteRepository venteRepository;
    private final ClientRepository clientRepository;
    private final ProduitService produitService;
    private final ProduitVenduRepository produitVenduRepository;

    public VenteService(VenteRepository venteRepository, ClientRepository clientRepository, ProduitService produitService, ProduitVenduRepository produitVenduRepository) {
        this.venteRepository = venteRepository;
        this.clientRepository = clientRepository;
        this.produitService = produitService;
        this.produitVenduRepository = produitVenduRepository;
    }

    public List<VenteDTO> getAllVentes() {
        return venteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public VenteDTO getVenteById(UUID id) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vente non trouvée"));
        return convertToDTO(vente);
    }

    public Vente createVente(Vente vente) {
        Client clt = clientRepository.getOne(UUID.fromString(vente.getId_client()));
        vente.setClient(clt);
        Vente retVente = venteRepository.save(vente);

        if (retVente != null && retVente.getProduits() != null) {
            List<ProduitDetail> liste = new ArrayList<>();

            for (ProduitVendu pr : retVente.getProduits()) {
                pr.setVente(retVente);
                Produit produit = produitService.getProduitById(UUID.fromString(pr.getId_produit())).orElse(null);
                pr.setProduit(produit);

                // Mise à jour du stock
                produitService.venteStock(produit.getId(), pr.getQuantite());
                produitVenduRepository.save(pr);

                // 🔹 Création du ProduitDetail pour chaque produit vendu
                ProduitDetail produitDetail = new ProduitDetail();
                produitDetail.setProduit(produit);
                produitDetail.setImei1(pr.getImei());
                produitDetail.setImei2(pr.getImei2());
                produitDetail.setNumSerie(pr.getNumero_serie());
                produitDetail.setClientId(clt.getId()); // ✅ Association au client
                produitDetail.setEtat("VENDU"); // optionnel, si tu veux marquer l’état
                liste.add(produitDetail);
            }

            // 🔹 Enregistrement des détails produits (IMEIs, num séries…)
            produitService.venteDetailProduit(liste);
        }

        return retVente;
    }
/*
    public Vente updateVente(UUID id, Vente vente) {
        Vente existingVente = getVenteById(id);
        // Mettre à jour les champs souhaités
        existingVente.setModePaiement(vente.getModePaiement());
        existingVente.setTotalHt(vente.getTotalHt());
        existingVente.setTotalTtc(vente.getTotalTtc());
        return venteRepository.save(existingVente);
    }
*/
    public void deleteVente(UUID id) {
        venteRepository.deleteById(id);
    }

    public VenteDTO convertToDTO(Vente vente) {
        VenteDTO dto = new VenteDTO();
        dto.setId(vente.getId());

        if (vente.getClient() != null) {
            Client client = new Client();
            client.setId(vente.getClient().getId());
            client.setNom(vente.getClient().getNom());
            dto.setClient(client);;
        }

        if (vente.getUtilisateur() != null) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(vente.getUtilisateur().getId());
            utilisateur.setNom(vente.getUtilisateur().getNom());
            dto.setUtilisateur(utilisateur);
        }

        dto.setDate(vente.getDate());
        dto.setModePaiement(vente.getModePaiement());
        dto.setTotalHt(vente.getTotalHt());
        dto.setTotalTtc(vente.getTotalTtc());
        dto.setCreatedAt(vente.getCreatedAt());
        dto.setUpdatedAt(vente.getUpdatedAt());

        // Conversion des produits vendus
        if (vente.getProduits() != null) {
            List<ProduitVenduDTO> produitsDTO = vente.getProduits().stream().map(p -> {
                ProduitVenduDTO pDto = new ProduitVenduDTO();
                pDto.setId(p.getId());

                // Produit simplifié
                if (p.getProduit() != null) {
                    ProduitDTO produitDTO = new ProduitDTO();
                    produitDTO.setId(p.getProduit().getId());
                    produitDTO.setNom(p.getProduit().getNom());
                    produitDTO.setMarque(p.getProduit().getMarque());
                    produitDTO.setCategorie(p.getProduit().getCategorie());
                    produitDTO.setPrixVente(p.getProduit().getPrixVente());
                    produitDTO.setTva(p.getProduit().getTva());
                    produitDTO.setStockActuel(p.getProduit().getStockActuel());
                    produitDTO.setImageUrl(p.getProduit().getImageUrl());
                    pDto.setProduit(produitDTO);
                }

                pDto.setQuantite(p.getQuantite());
                pDto.setPrixUnitaire(p.getPrixUnitaire());
                pDto.setTva(p.getTva());
                pDto.setTotalLigne(p.getTotalLigne());
                return pDto;
            }).collect(Collectors.toList());
            dto.setProduits(produitsDTO);
        }

        return dto;
    }

}
