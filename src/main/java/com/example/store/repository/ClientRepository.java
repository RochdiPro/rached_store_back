package com.example.store.repository;


import com.example.store.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findAllByOrderByCreatedAtDesc();
}
