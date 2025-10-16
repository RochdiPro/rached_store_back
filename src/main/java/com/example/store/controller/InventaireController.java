package com.example.store.controller;

import com.example.store.dto.MovementStockDTO;
import com.example.store.model.MouvementSoldeAvance;
import com.example.store.model.MouvementStock;
import com.example.store.service.InventaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventaire")
@RequiredArgsConstructor
public class InventaireController {
    private final InventaireService inventaireService;

    // ---------------- Mouvement Stock ----------------
    @PostMapping("/mouvements-stock")
    public MovementStockDTO createMouvementStock(@RequestBody MouvementStock mvt) {
        return inventaireService.createMouvementStock(mvt);
    }

    @GetMapping("/mouvements-stock")
    public List<MovementStockDTO> getMouvementsStock(@RequestParam(required = false) UUID produitId) {
        return inventaireService.getMouvementsStock(produitId);
    }

    // Suppression
    @DeleteMapping("/mouvements-stock/{id}")
    public ResponseEntity<Void> deleteMouvementStock(@PathVariable UUID id) {
        boolean deleted = inventaireService.deleteMouvementStock(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();   // 404 Not Found
        }
    }
    @GetMapping("/mouvements-stock/{id}")
    public MovementStockDTO getMouvementStockById(@PathVariable UUID id) {
        return inventaireService.getMouvementsStock(null).stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Mouvement stock non trouvé"));
    }

    // ---------------- Mouvement Solde ----------------
    @PostMapping("/mouvements-solde")
    public MouvementSoldeAvance createMouvementSolde(@RequestBody MouvementSoldeAvance mvt) {
        return inventaireService.createMouvementSoldeAvance(mvt);
    }

    @GetMapping("/mouvements-solde")
    public List<MouvementSoldeAvance> getMouvementsSolde(@RequestParam(required = false) UUID clientId) {
        return inventaireService.getMouvementsSoldeAvance(clientId);
    }

    @GetMapping("/mouvements-solde/{id}")
    public MouvementSoldeAvance getMouvementSoldeById(@PathVariable UUID id) {
        return inventaireService.getMouvementsSoldeAvance(null).stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Mouvement solde non trouvé"));
    }
}
