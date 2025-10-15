package com.example.store.service;

import com.example.store.dto.ReparationDTO;
import com.example.store.model.*;
import com.example.store.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReparationService {

    private final ReparationRepository reparationRepository;
    private final PieceRepository pieceRepository;
    private final ReparationDefautStandardRepository defautRepository;
    private final DefautStandardRepository defautStandardRepository;
    private final ClientRepository clientRepository;

    public ReparationService(ReparationRepository reparationRepository, PieceRepository pieceRepository, ReparationDefautStandardRepository defautRepository, DefautStandardRepository defautStandardRepository, ClientRepository clientRepository) {
        this.reparationRepository = reparationRepository;
        this.pieceRepository = pieceRepository;
        this.defautRepository = defautRepository;
        this.defautStandardRepository = defautStandardRepository;
        this.clientRepository = clientRepository;
    }

    public List<Reparation> getAll() {
        return reparationRepository.findAll();
    }

    public Reparation getById(long id) {
        return reparationRepository.findById(id).orElseThrow(() -> new RuntimeException("Réparation non trouvée"));
    }

    public Reparation create(ReparationDTO dto)
    {   // Récupérer le client
        Client client = clientRepository.findById(UUID.fromString(dto.getId_client()))
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        // Créer l'entité Reparation
        Reparation reparation = new Reparation();
        reparation.setClient(client);
        reparation.setDefaut(dto.getDefaut());
        reparation.setDescription(dto.getDescription());
        reparation.setPrix(dto.getPrix());
        reparation.setStatut(dto.getStatut() != null ? dto.getStatut() : "en_attente");
        reparation.setImageUrl(dto.getImageUrl());

        // Sauvegarder
        return reparationRepository.save(reparation);

    }

    public Reparation update(long id, Reparation reparation) {
        Reparation existante = getById(id); // récupère la réparation existante

        existante.setClient(reparation.getClient());
        existante.setDefaut(reparation.getDefaut());
        existante.setDescription(reparation.getDescription());
        existante.setPrix(reparation.getPrix());
        existante.setStatut(reparation.getStatut());
        existante.setImageUrl(reparation.getImageUrl());
        existante.setUpdatedAt(LocalDate.now());

        // Mise à jour des pièces
        if (reparation.getPieces() != null) {
            existante.getPieces().clear();
            existante.getPieces().addAll(reparation.getPieces());
            existante.getPieces().forEach(p -> p.setReparation(existante));
        }

        // Mise à jour des défauts standards
        if (reparation.getDefautsStandard() != null) {
            existante.getDefautsStandard().clear();
            existante.getDefautsStandard().addAll(reparation.getDefautsStandard());
            existante.getDefautsStandard().forEach(d -> d.setReparation(existante));
        }

        return reparationRepository.save(existante);
    }


    public void delete(long id) {
        reparationRepository.deleteById(id);
    }

    // Pièces
    public ReparationPiece addPiece(ReparationPiece piece) {
        return pieceRepository.save(piece);
    }

    public List<ReparationPiece> getPiecesByReparation(Long reparationId) {
        return pieceRepository.findByReparationId(reparationId);
    }

    public ReparationDefautStandard addDefautStandardToReparation(long reparationId, UUID defautId) {
        Reparation reparation = reparationRepository.getById(reparationId);
        DefautStandard defaut = defautStandardRepository.getById(defautId);

        ReparationDefautStandard rds = ReparationDefautStandard.builder()
                .reparation(reparation)
                .defautStandard(defaut)
                .build();

        return defautRepository.save(rds);
    }


    public List<ReparationDefautStandard> getDefautsByReparation(long reparationId) {
        return defautRepository.findByReparationId(reparationId);
    }
}
