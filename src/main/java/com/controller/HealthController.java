package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, String>> health() {
        System.out.println("DEBUG: Health check request received at /api/health");
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Service is healthy");
        return ResponseEntity.ok(response);
    }
}
