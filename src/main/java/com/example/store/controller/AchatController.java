package com.example.store.controller;

import com.example.store.dto.AchatDTO;
import com.example.store.model.Achat;
import com.example.store.model.ProduitAchete;
import com.example.store.service.AchatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/achats")
public class AchatController {

    private final AchatService achatService;


    public AchatController(AchatService achatService) {
        this.achatService = achatService;
    }

    @GetMapping
    public List<AchatDTO> getAll() {
        return achatService.getAll();
    }

    @GetMapping("/{id}")
    public AchatDTO getById(@PathVariable UUID id) {
        return achatService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Achat achat) {
         String id = achatService.create(achat);
        return ResponseEntity.ok(id);
    }

  /*  @PostMapping("/{achatId}/produits-achetes")
    public ProduitAchete createProduit(@PathVariable UUID achatId, @RequestBody ProduitAchete produit) {
        Achat achat = achatService.getById(achatId);
        produit.setAchat(achat);
       // return achatService.createProduit(produit);
    }*/
    // Supprimer un achat par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchat(@PathVariable UUID id) {
        try {
            achatService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 si non trouvé
        }
    }
}
