package com.example.store.repository;

import com.example.store.model.DefautStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DefautStandardRepository extends JpaRepository<DefautStandard, UUID> {
    List<DefautStandard> findByActifTrue();

}
