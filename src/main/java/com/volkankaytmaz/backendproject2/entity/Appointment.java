package com.volkankaytmaz.backendproject2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @ElementCollection
    @CollectionTable(name = "appointment_services",
            joinColumns = @JoinColumn(name = "appointment_id"))
    @Column(name = "service")
    private List<String> services;

    private String status;
    private String notes;
}

