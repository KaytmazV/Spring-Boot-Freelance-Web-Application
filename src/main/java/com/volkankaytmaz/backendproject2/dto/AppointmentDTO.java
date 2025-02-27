package com.volkankaytmaz.backendproject2.dto;

import com.volkankaytmaz.backendproject2.entity.Customer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppointmentDTO {
    private Long id;
    private Customer customer;
    private LocalDateTime appointmentDate;
    private List<String> services;
    private String status;
    private String notes;
}
