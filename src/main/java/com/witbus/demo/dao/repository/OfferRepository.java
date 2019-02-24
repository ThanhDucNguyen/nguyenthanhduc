package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query(value = "SELECT * FROM offer ORDER BY offer_id DESC Limit 5 ", nativeQuery = true)
    List<Offer> getOffer();

    @Query(value = "SELECT * FROM offer where offer_id = ?1 ", nativeQuery = true)
    Offer findByOffer(Long id);
}
