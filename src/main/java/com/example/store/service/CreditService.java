package com.example.store.service;



import com.example.store.model.*;
import com.example.store.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {
    private final CreditClientRepository creditClientRepo;
    private final CreditFournisseurRepository creditFournisseurRepo;
    private final PaiementClientRepository paiementClientRepo;
    private final PaiementFournisseurRepository paiementFournisseurRepo;
    private final ClientRepository clientRepo;
    private final FournisseurRepository fournisseurRepo;

    public CreditService(CreditClientRepository creditClientRepo, CreditFournisseurRepository creditFournisseurRepo, PaiementClientRepository paiementClientRepo, PaiementFournisseurRepository paiementFournisseurRepo, ClientRepository clientRepo, FournisseurRepository fournisseurRepo) {
        this.creditClientRepo = creditClientRepo;
        this.creditFournisseurRepo = creditFournisseurRepo;
        this.paiementClientRepo = paiementClientRepo;
        this.paiementFournisseurRepo = paiementFournisseurRepo;
        this.clientRepo = clientRepo;
        this.fournisseurRepo = fournisseurRepo;
    }

    // --- CRUD Client ---
    public List<CreditClient> getCreditsClient() {
        List<CreditClient> liste = creditClientRepo.findAll();
        return liste;
    }
    public CreditClient createCreditClient(CreditClient credit) {
        Client client  = clientRepo.findById(UUID.fromString(credit.getId_client()))
                .orElseThrow(() -> new RuntimeException("client non trouvé"));
        credit.setClient(client);
        credit.setMontantRestant(credit.getMontant());
        return creditClientRepo.save(credit);
    }
    public CreditClient updateCreditClient(UUID id, CreditClient credit) {

        Client client  = clientRepo.findById(UUID.fromString(credit.getId_client()))
                .orElseThrow(() -> new RuntimeException("client non trouvé"));

        CreditClient  found = creditClientRepo.findById(id).orElse(null);
        found.setClient(client);
        found.setMontant(credit.getMontant());
        found.setDateEcheance(credit.getDateEcheance());
        found.setDateOctroi(credit.getDateOctroi());
        return creditClientRepo.save(found);
    }
    public void deleteCreditClient(String id) { creditClientRepo.deleteById(UUID.fromString(id)); }

    public PaiementClient addPaiementClient(PaiementClient paiement) {
        // mise à jour montant restant et statut
        CreditClient credit = creditClientRepo.findById(UUID.fromString(paiement.getId_credit())).get();
       // CreditClient credit = paiement.getCredit();
        double restant = 0 ;

        if( credit.getMontantRestant() == null){
              restant = credit.getMontant() - paiement.getMontant();
        }
        else{
              restant = credit.getMontantRestant() - paiement.getMontant();
        }
        credit.setMontantRestant(Math.max(0, restant));
        credit.setStatut(restant <= 0 ? "paye" : "en_cours");
        credit.setId_client(credit.getClient().getId().toString());
        creditClientRepo.save(credit);
        return paiementClientRepo.save(paiement);
    }

    // --- CRUD Fournisseur ---
    public List<CreditFournisseur> getCreditsFournisseur() { return creditFournisseurRepo.findAll(); }
    public CreditFournisseur createCreditFournisseur(CreditFournisseur credit) {
        Fournisseur fournisseur  = fournisseurRepo.findById(UUID.fromString(credit.getId_fournisseur()))
                .orElseThrow(() -> new RuntimeException("fournisseur non trouvé"));
        credit.setFournisseur(fournisseur);
        return creditFournisseurRepo.save(credit);
    }
    public CreditFournisseur updateCreditFournisseur(UUID id, CreditFournisseur credit) {
        credit.setId(id);
        Fournisseur fournisseur  = fournisseurRepo.findById(UUID.fromString(credit.getId_fournisseur()))
                .orElseThrow(() -> new RuntimeException("fournisseur non trouvé"));
        credit.setFournisseur(fournisseur);
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

    public List<CreditFournisseur> getCreditsClientsNonPayes() {
        return creditFournisseurRepo.findByStatutNot("paye");
    }


    // Crédits fournisseurs non payés
    public List<CreditFournisseur> getCreditsFournisseursNonPayes() {
        return creditFournisseurRepo.findByStatutNot("paye");
    }
}
