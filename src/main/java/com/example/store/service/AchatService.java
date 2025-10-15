package com.example.store.service;

import com.example.store.dto.AchatDTO;
import com.example.store.dto.ProduitAcheteDTO;
import com.example.store.dto.ProduitDetailDTO;
import com.example.store.model.*;
import com.example.store.repository.AchatRepository;
import com.example.store.repository.FournisseurRepository;
import com.example.store.repository.ProduitAcheteRepository;
import com.example.store.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AchatService {

    private final AchatRepository achatRepo;
    private final ProduitAcheteRepository produitAcheteRepository;
    private final FournisseurRepository fournisseurRepo;
    private final ProduitRepository produitRepo;
    private final ProduitService produitService;


    public AchatService(AchatRepository achatRepo, ProduitAcheteRepository produitAcheteRepository, FournisseurRepository fournisseurRepo, ProduitRepository produitRepo, ProduitService produitService) {
        this.achatRepo = achatRepo;
        this.produitAcheteRepository = produitAcheteRepository;
        this.fournisseurRepo = fournisseurRepo;
        this.produitRepo = produitRepo;
        this.produitService = produitService;
    }

    public List<AchatDTO> getAll() {
        List<Achat> achats = achatRepo.findAll();
        List<AchatDTO> liste =  achats.stream()
                .map(this::toDto)   // Appel de la méthode normale dans le service
                .collect(Collectors.toList());
        return liste;
    }

    public AchatDTO getById(UUID id) {
        return achatRepo.findById(id)
                .map(this::toDto)   // Appel de la méthode normale dans le service
                .orElse(null);
    }

    public String create(Achat achat) {

        Fournisseur f = fournisseurRepo.getById(UUID.fromString(achat.getId_fournisseur()));
        achat.setFournisseur(f);
        Achat achatSaved = achatRepo.save(achat);

        if (achat.getProduits() != null && !achat.getProduits().isEmpty()) {
            List<ProduitDetail> liste = new ArrayList<ProduitDetail>();
            for (ProduitAchete pa : achat.getProduits()) {
                pa.setAchat(achatSaved);
                Produit produit = produitRepo.getById(UUID.fromString(pa.getId_produit()));
                pa.setProduit(produit);
                ProduitDetail produitDetail = new ProduitDetail();
                produitDetail.setProduit(produit);
                produitDetail.setImei1(pa.getImei());
                produitDetail.setImei2(pa.getImei2());
                produitDetail.setNumSerie(pa.getNumero_serie());
                produitDetail.setFournisseur(f.getId());
                produitDetail.setNom(produit.getNom());
                produitDetail.setCategorie(produit.getCategorie());
                produitDetail.setMarque(produit.getMarque());
                liste.add(produitDetail);
                produitService.updateStock(produit.getId(), pa.getQuantite());
                pa.setNom(produit.getNom());
                pa.setCategorie(produit.getCategorie());
                pa.setMarque(produit.getMarque());
                produitAcheteRepository.save(pa);
            }
            produitService.addDetailProduit(liste);
        }
        return achatSaved.getId().toString();
    }


    /*   public ProduitAchete createProduit(ProduitAchete produit) {
           return produitRepo.save(produit);
       }*/
    public void deleteById(UUID id) {
        achatRepo.deleteById(id);
    }




    // Méthode d'instance pour convertir Achat → AchatDTO
    public AchatDTO toDto(Achat achat) {
        AchatDTO dto = new AchatDTO();
        dto.setId(achat.getId());
        dto.setDate(achat.getDate());
        dto.setTotalHt(achat.getTotalHt());
        dto.setTva(achat.getTva());
        dto.setTotalTtc(achat.getTotalTtc());
        dto.setFournisseur(achat.getFournisseur() != null ? achat.getFournisseur().getNom() : null);
        dto.setFournisseur_id(achat.getFournisseur() != null ? achat.getFournisseur().getId() : null);
        dto.setNumFacture(achat.getNumFacture());


        // Mapping des produits achetés
        List<ProduitAcheteDTO> produitsDto = new ArrayList<>();
        if (achat.getProduits() != null) {
            for (ProduitAchete pa : achat.getProduits()) {
                produitsDto.add(toProduitAcheteDto(pa));
            }
        }
        dto.setProduitAchete(produitsDto);

        return dto;
    }

    // Méthode d'instance pour convertir ProduitAchete → ProduitAcheteDTO
    public ProduitAcheteDTO toProduitAcheteDto(ProduitAchete entity) {
        ProduitAcheteDTO dto = new ProduitAcheteDTO();
        dto.setId(entity.getId());
        dto.setQuantite(entity.getQuantite());
        dto.setPrixUnitaire(entity.getPrixUnitaire());
        dto.setTva(entity.getTva());
        dto.setTotalLigne(entity.getTotalLigne());
        dto.setNom(entity.getNom());
        dto.setCategorie(entity.getCategorie());
        dto.setMarque(entity.getMarque());
        List<ProduitDetailDTO> detailsDto = new ArrayList<>();
        ProduitDetailDTO detailDTO = new ProduitDetailDTO();
        detailDTO.setImei1(entity.getImei());
        detailDTO.setImei2(entity.getImei2());
        detailDTO.setNumSerie(entity.getNumero_serie());
        detailDTO.setNom(entity.getNom());
        detailDTO.setCategorie(entity.getCategorie());
        detailDTO.setMarque(entity.getMarque());
        if (entity.getId_fornisseur() != null) {
            detailDTO.setFournisseur(UUID.fromString(entity.getId_fornisseur()));
        }
        detailsDto.add(detailDTO);
        dto.setDetails(detailsDto);

        return dto;
    }
}
