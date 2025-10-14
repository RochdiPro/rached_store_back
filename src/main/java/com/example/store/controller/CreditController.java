package com.example.store.controller;


import com.example.store.model.CreditClient;
import com.example.store.model.CreditFournisseur;
import com.example.store.model.PaiementClient;
import com.example.store.model.PaiementCreditFournisseur;
import com.example.store.service.CreditService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin("*")
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) { this.creditService = creditService; }

    @GetMapping("/clients")
    public List<CreditClient> getCreditsClient() { return creditService.getCreditsClient(); }

    @PostMapping("/clients")
    public CreditClient createCreditClient(@RequestBody CreditClient credit) {
        return creditService.createCreditClient(credit);
    }

    @PutMapping("/clients/{id}")
    public CreditClient updateCreditClient(@PathVariable UUID id, @RequestBody CreditClient credit) {
        return creditService.updateCreditClient(id, credit);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteCreditClient(@PathVariable String id) { creditService.deleteCreditClient(id); }

    @PostMapping("/clients/paiements")
    public PaiementClient addPaiementClient(@RequestBody PaiementClient paiement) { return creditService.addPaiementClient(paiement); }

    // --- mêmes endpoints pour fournisseurs ---
    @GetMapping("/fournisseurs")
    public List<CreditFournisseur> getCreditsFournisseur() { return creditService.getCreditsFournisseur(); }

    @PostMapping("/fournisseurs")
    public CreditFournisseur createCreditFournisseur(@RequestBody CreditFournisseur credit) { return creditService.createCreditFournisseur(credit); }

    @PutMapping("/fournisseurs/{id}")
    public CreditFournisseur updateCreditFournisseur(@PathVariable UUID id, @RequestBody CreditFournisseur credit) {
        return creditService.updateCreditFournisseur(id, credit);
    }

    @DeleteMapping("/fournisseurs/{id}")
    public void deleteCreditFournisseur(@PathVariable String id) { creditService.deleteCreditFournisseur(id); }

    @PostMapping("/fournisseurs/paiements")
    public PaiementCreditFournisseur addPaiementFournisseur(@RequestBody PaiementCreditFournisseur paiement) {
        return creditService.addPaiementFournisseur(paiement);
    }
}

