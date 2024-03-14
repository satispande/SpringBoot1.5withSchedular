package com.scheduler.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scheduler.model.SyndicationViewEntity;

@Repository
public interface SyndicationViewEntityRepository extends JpaRepository<SyndicationViewEntity, Long>{

	   
	    List<SyndicationViewEntity> findByLastUpdatedDateGreaterThan(Date startDate);
	    
	    List<SyndicationViewEntity> findByLastUpdatedDateBetween(Date startDate, Date endDate);


}
