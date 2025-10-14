package com.example.store.repository;

import com.example.store.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {

    Optional<Utilisateur> findByLogin(String login);
    boolean existsByLogin(String login);
}
