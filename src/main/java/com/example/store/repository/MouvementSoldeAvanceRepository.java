package com.example.store.repository;

import com.example.store.model.MouvementSoldeAvance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MouvementSoldeAvanceRepository extends JpaRepository<MouvementSoldeAvance, UUID> {
    List<MouvementSoldeAvance> findByClientId(UUID clientId);
}
