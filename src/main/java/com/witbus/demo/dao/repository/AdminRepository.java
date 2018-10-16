package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where user_name=?1 and password=?2 and role = 0", nativeQuery = true)
    User findByUserNameAndPassword(String userName, String password, Integer role);
}
