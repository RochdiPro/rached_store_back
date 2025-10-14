package com.example.store.repository;

import com.example.store.model.ReparationPiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface PieceRepository extends JpaRepository<ReparationPiece, UUID> {
    List<ReparationPiece> findByReparationId(UUID reparationId);
}
