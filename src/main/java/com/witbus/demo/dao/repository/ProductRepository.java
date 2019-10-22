package com.witbus.demo.dao.repository;

import com.witbus.demo.dao.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select *from product where id =?1", nativeQuery = true)
    Product findProductById(Long id);

    @Query(value = "select *from product where type =?1", nativeQuery = true)
    List<Product> listProductByType(String type);

    @Query(value = "select *from product where type =?1 and origin=?2", nativeQuery = true)
    List<Product> listProductByTypeAndOrigin(String type, String origin);
}
