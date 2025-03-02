package com.volkankaytmaz.backendproject2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/test")
    public String publicTest() {
        return "Bu herkese açık bir test endpoint'idir!";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String securedTest() {
        return "Bu güvenli bir test endpoint'idir!";
    }
}

