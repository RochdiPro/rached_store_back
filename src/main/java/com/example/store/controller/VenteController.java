package com.example.store.controller;

import com.example.store.dto.VenteDTO;
import com.example.store.model.ProduitVendu;
import com.example.store.model.Vente;
import com.example.store.service.ProduitVenduService;
import com.example.store.service.VenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ventes")
@CrossOrigin(origins = "*") // autorise toutes les origines (Angular local)
public class VenteController {

    private final VenteService venteService;
    private final ProduitVenduService produitVenduService;

    public VenteController(VenteService venteService, ProduitVenduService produitVenduService) {
        this.venteService = venteService;
        this.produitVenduService = produitVenduService;
    }

    // -------------------- VENTES --------------------

    @GetMapping
    public ResponseEntity<List<VenteDTO>> getAllVentes() {
        List<VenteDTO> ventes = venteService.getAllVentes();
        return ResponseEntity.ok(ventes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteDTO> getVenteById(@PathVariable UUID id) {
        VenteDTO vente = venteService.getVenteById(id);
        return ResponseEntity.ok(vente);
    }

    @PostMapping
    public  ResponseEntity<String>  createVente(@RequestBody Vente vente) {
        Vente newVente = venteService.createVente(vente);
        return ResponseEntity.ok(newVente.getId().toString());

    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Vente> updateVente(@PathVariable UUID id, @RequestBody Vente vente) {
        Vente updatedVente = venteService.updateVente(id, vente);
        return ResponseEntity.ok(updatedVente);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable UUID id) {
        venteService.deleteVente(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------- PRODUITS VENDUS --------------------

   /* @GetMapping("/{venteId}/produits-vendus")
    public ResponseEntity<List<ProduitVendu>> getProduitsVendus(@PathVariable UUID venteId) {
        Vente vente = venteService.getVenteById(venteId);
        List<ProduitVendu> produitsVendus = produitVenduService.getProduitsVendusByVente(vente);
        return ResponseEntity.ok(produitsVendus);
    }*/

    @PostMapping("/produits-vendus")
    public ResponseEntity<ProduitVendu> createProduitVendu(@RequestBody ProduitVendu produitVendu) {
        ProduitVendu newProduitVendu = produitVenduService.createProduitVendu(produitVendu);
        return ResponseEntity.ok(newProduitVendu);
    }

    @DeleteMapping("/produits-vendus/{id}")
    public ResponseEntity<Void> deleteProduitVendu(@PathVariable UUID id) {
        produitVenduService.deleteProduitVendu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jour")
    public List<VenteDTO> getVentesJour(@RequestParam String date) {
        return venteService.getVentesByDate(LocalDate.parse(date));
    }

    @GetMapping("/mois")
    public List<VenteDTO> getVentesDepuis(@RequestParam String fromDate) {
        return venteService.getVentesFromDate(LocalDate.parse(fromDate));
    }


}
