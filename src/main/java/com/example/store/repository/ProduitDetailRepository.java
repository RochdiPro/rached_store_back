package com.example.store.repository;

 import com.example.store.model.ProduitDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProduitDetailRepository extends JpaRepository<ProduitDetail, UUID> {

}
