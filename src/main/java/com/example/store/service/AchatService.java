package com.example.store.service;

import com.example.store.dto.AchatDTO;
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
        return achatRepo.findAll().stream()
                .map(a -> new AchatDTO(a))
                .collect(Collectors.toList());    }

    public AchatDTO getById(UUID id) {
        return achatRepo.findById(id)
                .map(achat -> new AchatDTO(achat))
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
                liste.add(produitDetail);
                produitService.updateStock(produit.getId(),pa.getQuantite());
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


}
