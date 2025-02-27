package com.volkankaytmaz.backendproject2.repository;

import com.volkankaytmaz.backendproject2.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
