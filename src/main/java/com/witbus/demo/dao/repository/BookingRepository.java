package com.witbus.demo.dao.repository;


import com.witbus.demo.dao.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
