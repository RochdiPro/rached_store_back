package com.example.store.repository;

import com.example.store.model.Achat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AchatRepository extends JpaRepository<Achat, UUID> {}
