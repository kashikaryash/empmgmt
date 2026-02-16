package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*")
public class RootController {

    @GetMapping("/")
    public String healthCheck() {
        return "Backend is running!";
    }
}
