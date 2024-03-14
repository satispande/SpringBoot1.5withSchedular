package com.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scheduler.model.ClientNotificationConfig;
import com.scheduler.repository.ClientNotificationConfigRepository;

@Service
public class ClientNotificationService {

    @Autowired
    private ClientNotificationConfigRepository configRepository;

    @Autowired
    private EmailServiceImpl emailService;

    public void registerClient(ClientNotificationConfig config) throws Exception {
        configRepository.save(config);
    }

    
}

