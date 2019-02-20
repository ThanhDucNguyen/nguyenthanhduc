package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query(value = "SELECT * FROM bus WHERE  bus_owner_id = ?1", nativeQuery = true)
    List<Bus> listById(Long id);

    @Query(value = "select *from bus where bus_id =?1", nativeQuery = true)
    Bus findBusById(Long id);
}
