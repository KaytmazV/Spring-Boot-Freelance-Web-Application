package com.volkankaytmaz.backendproject2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/public/test")
    public String publicTest() {
        return "Hello, this is a public endpoint!";
    }

    @GetMapping("/api/secured/test")
    @PreAuthorize("hasRole('USER')")
    public String securedTest() {
        return "Hello, this is a secured endpoint!";
    }
}

