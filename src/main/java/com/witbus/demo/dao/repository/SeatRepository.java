package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT * from  bus as b" +
            " inner join seats as s on b.bus_id = s.bus_id and s.seat_status = false \n" +
            " WHERE b.bus_origin like %?1% AND s.seat_type like %?2%",nativeQuery = true)
    List<Seat> getSeatAvailable (String  busOrigin, String seatType);
//    @Query(value = "SELECT * from seat where status='false' ",nativeQuery = true)
//    Seat
}
