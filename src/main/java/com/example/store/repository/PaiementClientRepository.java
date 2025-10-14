package com.example.store.repository;

import com.example.store.model.PaiementClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementClientRepository extends JpaRepository<PaiementClient, String> {}

