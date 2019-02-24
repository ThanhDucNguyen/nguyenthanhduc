package com.witbus.demo.dao.repository;


import com.witbus.demo.dao.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "SELECT * FROM booking WHERE  user_id = ?1", nativeQuery = true)
    List<Booking> listByUserId(Long id);
    @Query(value = "SELECT * FROM booking WHERE  seat_id = ?1", nativeQuery = true)
    List<Booking> listBySeatId(Long id);
    @Query(value = "SELECT * FROM booking WHERE  seat_id = ?1", nativeQuery = true)
    Booking findbyBooking(Long id);

}
