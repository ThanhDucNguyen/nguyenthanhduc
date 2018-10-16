package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.BusOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusOwnerRepository extends JpaRepository<BusOwner, Long> {
}
