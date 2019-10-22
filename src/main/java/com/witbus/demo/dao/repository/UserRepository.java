package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select *from user where username =?1 and password=?2", nativeQuery = true)
    User login(String username, String password);
}
