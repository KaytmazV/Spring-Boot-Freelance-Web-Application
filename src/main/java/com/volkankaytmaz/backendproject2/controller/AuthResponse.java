package com.volkankaytmaz.backendproject2.controller;

public class AuthResponse {
    private String token;

    // Parametreli yapıcı
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter (isteğe bağlı)
    public void setToken(String token) {
        this.token = token;
    }
}