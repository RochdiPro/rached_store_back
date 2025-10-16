package com.example.store.repository;

import com.example.store.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, UUID> {
    // Optionnel : recherche par code barre ou nom
    boolean existsByCodeBarre(String codeBarre);

    List<Produit> findByNomContainingIgnoreCaseOrMarqueContainingIgnoreCaseOrCodeBarreContainingIgnoreCase(
            String nom, String marque, String codeBarre);

    List<Produit> findByStockActuelLessThanEqual(Double stockCritique);

    @Query("SELECT COUNT(p) FROM Produit p")
    long countProduits();

    @Query("SELECT COUNT(p) FROM Produit p WHERE p.stockActuel <= p.stockCritique")
    long countStockCritique();
}
