package com.volkankaytmaz.backendproject2.dto;

import lombok.Data;

/**
 * Özet müşteri bilgilerini içeren DTO.
 * Appointment içinde kullanılarak sonsuz döngüyü önler.
 */
@Data
public class CustomerSummaryDTO {
    private Long id;
    private String name;
    private String phone;
}

