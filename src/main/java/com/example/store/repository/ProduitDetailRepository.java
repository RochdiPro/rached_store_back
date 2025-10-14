package com.example.store.repository;

 import com.example.store.model.ProduitDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.util.List;
 import java.util.Optional;
 import java.util.UUID;

@Repository
public interface ProduitDetailRepository extends JpaRepository<ProduitDetail, UUID> {

 List<ProduitDetail> findByImei1AndImei2AndNumSerie(String imei1, String imei2, String numSerie);
}
