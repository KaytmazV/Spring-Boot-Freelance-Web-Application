package com.volkankaytmaz.backendproject2.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppointmentDTO {
    private Long id;
    private Long customerId; // Customer'ın sadece ID'sini tutuyoruz
    private String customerName; // İhtiyaç duyulan müşteri bilgilerini ekleyebilirsiniz
    private LocalDateTime appointmentDate;
    private List<String> services;
    private String status;
    private String notes;
}

