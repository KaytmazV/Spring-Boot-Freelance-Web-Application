package com.volkankaytmaz.backendproject2.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String name;
    private String username;
    private String password;
    private String email;
}

