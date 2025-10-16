package com.example.store.service;

import com.example.store.dto.ClientDTO;
import com.example.store.dto.ProduitDTO;
import com.example.store.dto.ProduitVenduDTO;
import com.example.store.dto.VenteDTO;
import com.example.store.model.*;
import com.example.store.repository.ClientRepository;
import com.example.store.repository.ProduitVenduRepository;
import com.example.store.repository.VenteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<Vente> ventes = venteRepository.findAll();
        return ventes.stream()
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
                pr.setNom(produit.getNom());
                pr.setCategorie(produit.getCategorie());
                pr.setMarque(produit.getMarque());
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
                produitDetail.setNom(produit.getNom());
                produitDetail.setMarque(produit.getMarque());
                produitDetail.setCategorie(produit.getCategorie());
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

    public List<VenteDTO> getVentesByDate(LocalDate date) {

        List<Vente> ventes = venteRepository.findByDate(date);
        return ventes.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<VenteDTO> getVentesFromDate(LocalDate fromDate) {

        List<Vente> ventes = venteRepository.findByDateGreaterThanEqual(fromDate);
        return ventes.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public void deleteVente(UUID id) {
        venteRepository.deleteById(id);
    }

    private VenteDTO convertToDTO(Vente vente) {
        VenteDTO dto = new VenteDTO();
        dto.setId(vente.getId());
        dto.setClient(clienttoDTO(vente.getClient()));
        //dto.setUtilisateur(vente.getUtilisateur());
        dto.setDate(vente.getDate());
        dto.setModePaiement(vente.getModePaiement());
        dto.setTotalHt(vente.getTotalHt());
        dto.setTotalTtc(vente.getTotalTtc());
        dto.setCreatedAt(vente.getCreatedAt());
        dto.setUpdatedAt(vente.getUpdatedAt());


        dto.setProduits(mapProduits(vente.getProduits()));
        return dto;
    }

    private List<ProduitVenduDTO> mapProduits(List<ProduitVendu> produits) {
        if (produits == null) return new ArrayList<>();
        return produits.stream().map(this::mapProduit).toList();
    }

    private ProduitVenduDTO mapProduit(ProduitVendu prod) {
        ProduitVenduDTO dto = new ProduitVenduDTO();
        dto.setQuantite(prod.getQuantite());
        dto.setPrixUnitaire(prod.getPrixUnitaire());
        dto.setTva(prod.getTva());
        dto.setTotalLigne(prod.getTotalLigne());
        dto.setImei(prod.getImei());
        dto.setImei2(prod.getImei2());
        dto.setNumero_serie(prod.getNumero_serie());
        dto.setNom(prod.getNom());
        dto.setCategorie(prod.getCategorie());
        dto.setMarque(prod.getMarque());
        return dto;
    }



    ClientDTO clienttoDTO(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setType(client.getType());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setRaisonSociale(client.getRaisonSociale());
        dto.setCin(client.getCin());
        dto.setNumFiscal(client.getNumFiscal());
        dto.setTel(client.getTel());
        dto.setEmail(client.getEmail());
        dto.setAdresse(client.getAdresse());
        dto.setPointsFidelite(client.getPointsFidelite());
        dto.setCredit(client.getCredit());
        dto.setSoldeAvance(client.getSoldeAvance());
        dto.setCreatedAt(client.getCreatedAt());
        dto.setUpdatedAt(client.getUpdatedAt());

        return dto;
    }
}
