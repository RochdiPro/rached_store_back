package com.example.store.repository;

import com.example.store.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock, UUID> {
    List<MouvementStock> findByProduitId(UUID produitId);
}
