package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query(value = "SELECT * FROM bus GROUP BY bus_origin and bus_destination", nativeQuery = true)
    List<Bus> listBus();

    @Query(value = "select *from bus where bus_id =?1", nativeQuery = true)
    Bus findBusById(Long id);

    @Query(value = "SELECT * FROM bus WHERE   bus_origin= ?1 and bus_destination=?2 and bus_date=?3", nativeQuery = true)
    List<Bus> getBus(String origin,String destination,String date);
}
