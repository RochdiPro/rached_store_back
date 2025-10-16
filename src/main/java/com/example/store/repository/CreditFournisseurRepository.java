package com.example.store.repository;

import com.example.store.model.CreditFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditFournisseurRepository extends JpaRepository<CreditFournisseur, String> {
    List<CreditFournisseur> findByStatutNot(String statut);

}
