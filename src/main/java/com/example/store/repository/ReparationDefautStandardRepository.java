package com.example.store.repository;

import com.example.store.model.ReparationDefautStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReparationDefautStandardRepository extends JpaRepository<ReparationDefautStandard, UUID> {
    List<ReparationDefautStandard> findByReparationId(long reparationId);
}
