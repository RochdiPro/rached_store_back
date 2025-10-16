package com.example.store.repository;

import com.example.store.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface VenteRepository extends JpaRepository<Vente, UUID> {

      //List<Vente> findByClientId(UUID clientId);
      List<Vente> findByDate(LocalDate date);
    List<Vente> findByDateGreaterThanEqual(LocalDate date);
}
