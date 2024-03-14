package com.scheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scheduler.model.ClientNotificationConfig;

@Repository
public interface ClientNotificationConfigRepository extends JpaRepository<ClientNotificationConfig, Long>{

    List<ClientNotificationConfig> findByFrequency(@Param("frequency") String frequency);

}
