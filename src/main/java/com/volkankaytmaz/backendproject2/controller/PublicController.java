package com.volkankaytmaz.backendproject2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/home")
    public String home() {
        return "Herkese açık ana sayfaya hoş geldiniz!";
    }
}

