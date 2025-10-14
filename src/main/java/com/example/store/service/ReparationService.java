package com.example.store.service;

import com.example.store.model.DefautStandard;
import com.example.store.model.Reparation;
import com.example.store.model.ReparationDefautStandard;
import com.example.store.model.ReparationPiece;
import com.example.store.repository.DefautStandardRepository;
import com.example.store.repository.ReparationRepository;
import com.example.store.repository.PieceRepository;
import com.example.store.repository.ReparationDefautStandardRepository;
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

    public ReparationService(ReparationRepository reparationRepository, PieceRepository pieceRepository, ReparationDefautStandardRepository defautRepository, DefautStandardRepository defautStandardRepository) {
        this.reparationRepository = reparationRepository;
        this.pieceRepository = pieceRepository;
        this.defautRepository = defautRepository;
        this.defautStandardRepository = defautStandardRepository;
    }

    public List<Reparation> getAll() {
        return reparationRepository.findAll();
    }

    public Reparation getById(UUID id) {
        return reparationRepository.findById(id).orElseThrow(() -> new RuntimeException("Réparation non trouvée"));
    }

    public Reparation create(Reparation reparation) {
        return reparationRepository.save(reparation);
    }

    public Reparation update(UUID id, Reparation reparation) {
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


    public void delete(UUID id) {
        reparationRepository.deleteById(id);
    }

    // Pièces
    public ReparationPiece addPiece(ReparationPiece piece) {
        return pieceRepository.save(piece);
    }

    public List<ReparationPiece> getPiecesByReparation(UUID reparationId) {
        return pieceRepository.findByReparationId(reparationId);
    }

    public ReparationDefautStandard addDefautStandardToReparation(UUID reparationId, UUID defautId) {
        Reparation reparation = reparationRepository.getById(reparationId);
        DefautStandard defaut = defautStandardRepository.getById(defautId);

        ReparationDefautStandard rds = ReparationDefautStandard.builder()
                .reparation(reparation)
                .defautStandard(defaut)
                .build();

        return defautRepository.save(rds);
    }


    public List<ReparationDefautStandard> getDefautsByReparation(UUID reparationId) {
        return defautRepository.findByReparationId(reparationId);
    }
}
