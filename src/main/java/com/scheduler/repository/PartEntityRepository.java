package com.scheduler.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scheduler.model.PartEntity;

@Repository
public interface PartEntityRepository extends JpaRepository<PartEntity, Long>{

//	   @Query("SELECT p FROM Product p WHERE p.lastUpdatedTimestamp > :startDate")
//	    List<Product> findAllUpdatedAfterDate(@Param("startDate") Date startDate);
	   
	    List<PartEntity> findByLastModifiedDateGreaterThan(Date startDate);
	    
	    List<PartEntity> findByLastModifiedDateBetween(Date startDate, Date endDate);



//	    @Query("SELECT p FROM Product p WHERE p.lastUpdatedTimestamp BETWEEN :startDate AND :endDate")
//	    List<Product> findAllUpdatedBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
