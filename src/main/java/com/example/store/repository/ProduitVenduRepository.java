package com.example.store.repository;

import com.example.store.model.ProduitVendu;
import com.example.store.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProduitVenduRepository extends JpaRepository<ProduitVendu, UUID> {
    List<ProduitVendu> findByVente(Vente vente);
}
