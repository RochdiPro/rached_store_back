package com.example.store.repository;

import com.example.store.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FournisseurRepository extends JpaRepository<Fournisseur, UUID> {
    List<Fournisseur> findAllByOrderByCreatedAtDesc();
}
