package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.BusOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusOwnerRepository extends JpaRepository<BusOwner, Long> {
    @Query(value = "select *from bus_owner where bus_owner_id =?1", nativeQuery = true)
    BusOwner findBusOwnerById(Long id);
}
