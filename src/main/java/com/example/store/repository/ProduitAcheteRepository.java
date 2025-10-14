package com.example.store.repository;

import com.example.store.model.ProduitAchete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProduitAcheteRepository extends JpaRepository<ProduitAchete, UUID> {}
