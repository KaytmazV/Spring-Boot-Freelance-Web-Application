package com.volkankaytmaz.backendproject2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/test")
    public String publicEndpoint() {
        return "Bu bir public endpoint'tir.";
    }

    @GetMapping("/private/test")
    public String privateEndpoint(Authentication authentication) {
        return "Merhaba, " + authentication.getName() + "! Bu bir private endpoint'tir.";
    }

    @GetMapping("/admin/test")
    public String adminEndpoint() {
        return "Bu bir admin endpoint'idir.";
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint(Authentication authentication) {
        return ResponseEntity.ok("Merhaba, " + authentication.getName() + "! Bu korumalı bir endpoint'tir.");
    }
}

