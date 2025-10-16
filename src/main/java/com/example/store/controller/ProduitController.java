package com.example.store.controller;

import com.example.store.dto.ProduitDTO;
import com.example.store.model.Produit;
import com.example.store.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable UUID id) {
        return produitService.getProduitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.createProduit(produit);
    }

    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable UUID id, @RequestBody Produit produit) {
        return produitService.updateProduit(id, produit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable UUID id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
    // 🔹 Rechercher par mot-clé (nom, marque ou code-barre)
    @GetMapping("/search")
    public List<Produit> searchProduits(@RequestParam String term) {
        return produitService.searchProduits(term);
    }

    // 🔹 Produits dont le stock est critique (stock_actuel <= stock_critique)
    @GetMapping("/stock/critique")
    public List<Produit> getStocksCritiques() {
        return produitService.getStocksCritiques();
    }

    // 🔹 Mettre à jour uniquement le stock
    @PatchMapping("/{id}/stock")
    public ResponseEntity<Produit> updateStock(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> body
    ) {
        int nouveauStock = Integer.valueOf(body.get("stock_actuel").toString());
        Produit updated = produitService.updateStock(id, nouveauStock);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/count")
    public Map<String, Long> getCount() {
        return produitService.getTotalProduits();
    }

    @GetMapping("/stock-critique")
    public Map<String, Long> getStockCritique() {
        return produitService.getStockCritique();
    }

}
