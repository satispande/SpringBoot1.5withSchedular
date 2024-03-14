package com.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.model.ClientNotificationConfig;
import com.scheduler.service.ClientNotificationService;

@RestController
@RequestMapping("/api")
public class ClientNotificationController {

    @Autowired
    private ClientNotificationService clientNotificationService;

    @PostMapping("/registerClientForNotification")
    public ResponseEntity<String> registerClient(@RequestBody ClientNotificationConfig config) {
        try {
            clientNotificationService.registerClient(config);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed: " + e.getMessage());
        }
    }
}
