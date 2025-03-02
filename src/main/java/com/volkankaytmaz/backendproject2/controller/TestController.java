package com.volkankaytmaz.backendproject2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/public/test")
    public String publicEndpoint() {
        return "Bu bir public endpoint'tir.";
    }

    @GetMapping("/api/private/test")
    public String privateEndpoint(Authentication authentication) {
        return "Merhaba, " + authentication.getName() + "! Bu bir private endpoint'tir.";
    }

    @GetMapping("/api/admin/test")
    public String adminEndpoint() {
        return "Bu bir admin endpoint'idir.";
    }
}

