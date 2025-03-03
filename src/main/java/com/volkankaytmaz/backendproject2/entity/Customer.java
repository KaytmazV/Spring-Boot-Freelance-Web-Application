package com.volkankaytmaz.backendproject2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @Column(name = "last_visit")
    private LocalTime lastVisit;

    @Column(name = "next_appointment")
    private LocalDateTime nextAppointment;

    @ElementCollection
    @CollectionTable(name = "customer_treatments",
            joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "treatment")
    private List<String> treatments;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Appointment> appointments;
}

