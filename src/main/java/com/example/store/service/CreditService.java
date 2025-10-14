package com.example.store.service;



import com.example.store.model.CreditClient;
import com.example.store.model.CreditFournisseur;
import com.example.store.model.PaiementClient;
import com.example.store.model.PaiementCreditFournisseur;
import com.example.store.repository.CreditClientRepository;
import com.example.store.repository.CreditFournisseurRepository;
import com.example.store.repository.PaiementClientRepository;
import com.example.store.repository.PaiementFournisseurRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {
    private final CreditClientRepository creditClientRepo;
    private final CreditFournisseurRepository creditFournisseurRepo;
    private final PaiementClientRepository paiementClientRepo;
    private final PaiementFournisseurRepository paiementFournisseurRepo;

    public CreditService(CreditClientRepository ccr,
                         CreditFournisseurRepository cfr,
                         PaiementClientRepository pcr,
                         PaiementFournisseurRepository pfr) {
        this.creditClientRepo = ccr;
        this.creditFournisseurRepo = cfr;
        this.paiementClientRepo = pcr;
        this.paiementFournisseurRepo = pfr;
    }

    // --- CRUD Client ---
    public List<CreditClient> getCreditsClient() { return creditClientRepo.findAll(); }
    public CreditClient createCreditClient(CreditClient credit) { return creditClientRepo.save(credit); }
    public CreditClient updateCreditClient(UUID id, CreditClient credit) {
        credit.setId(id);
        return creditClientRepo.save(credit);
    }
    public void deleteCreditClient(String id) { creditClientRepo.deleteById(id); }

    public PaiementClient addPaiementClient(PaiementClient paiement) {
        // mise à jour montant restant et statut
        CreditClient credit = paiement.getCredit();
        double restant = credit.getMontantRestant() - paiement.getMontant();
        credit.setMontantRestant(Math.max(0, restant));
        credit.setStatut(restant <= 0 ? "paye" : "en_cours");
        creditClientRepo.save(credit);
        return paiementClientRepo.save(paiement);
    }

    // --- CRUD Fournisseur ---
    public List<CreditFournisseur> getCreditsFournisseur() { return creditFournisseurRepo.findAll(); }
    public CreditFournisseur createCreditFournisseur(CreditFournisseur credit) { return creditFournisseurRepo.save(credit); }
    public CreditFournisseur updateCreditFournisseur(UUID id, CreditFournisseur credit) {
        credit.setId(id);
        return creditFournisseurRepo.save(credit);
    }
    public void deleteCreditFournisseur(String id) { creditFournisseurRepo.deleteById(id); }

    public PaiementCreditFournisseur addPaiementFournisseur(PaiementCreditFournisseur paiement) {
        CreditFournisseur credit = paiement.getCreditFournisseur();
        double restant = credit.getMontantRestant() - paiement.getMontant();
        credit.setMontantRestant(Math.max(0, restant));
        credit.setStatut(restant <= 0 ? "paye" : "en_cours");
        creditFournisseurRepo.save(credit);
        return paiementFournisseurRepo.save(paiement);
    }
}
