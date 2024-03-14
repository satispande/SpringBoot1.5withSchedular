package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scheduler.model.Product;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.lastUpdatedTimestamp > :startDate")
    List<Product> findAllUpdatedAfterDate(@Param("startDate") Date startDate);

    @Query("SELECT p FROM Product p WHERE p.lastUpdatedTimestamp BETWEEN :startDate AND :endDate")
    List<Product> findAllUpdatedBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
