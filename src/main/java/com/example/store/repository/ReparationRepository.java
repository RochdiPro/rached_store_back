package com.example.store.repository;

import com.example.store.model.Reparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ReparationRepository extends JpaRepository<Reparation, Long> {
    List<Reparation> findByClientId(UUID clientId);
    //List<Reparation> findByCodeReparationContainingIgnoreCase(String code);
}
