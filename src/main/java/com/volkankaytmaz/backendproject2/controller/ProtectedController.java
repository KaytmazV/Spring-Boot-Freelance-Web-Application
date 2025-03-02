package com.volkankaytmaz.backendproject2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {

    @GetMapping
    public String getProtectedInfo() {
        return "Bu, korumalı bir endpoint'tir. Sadece kimliği doğrulanmış kullanıcılar erişebilir.";
    }
}

