package com.example.store.repository;


import com.example.store.model.Personnalisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonnalisationRepository extends JpaRepository<Personnalisation, UUID> { }