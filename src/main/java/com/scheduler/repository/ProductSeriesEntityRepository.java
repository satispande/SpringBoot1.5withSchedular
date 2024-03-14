package com.scheduler.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scheduler.model.ClientNotificationConfig;
import com.scheduler.model.PartEntity;
import com.scheduler.model.ProductSeriesEntity;

@Repository
public interface ProductSeriesEntityRepository extends JpaRepository<ProductSeriesEntity, Long>{

    List<ProductSeriesEntity> findByLastModifiedDateGreaterThan(Date startDate);
    
    List<ProductSeriesEntity> findByLastModifiedDateBetween(Date startDate, Date endDate);

}
