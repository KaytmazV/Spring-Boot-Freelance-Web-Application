package com.volkankaytmaz.backendproject2.controller;


import com.volkankaytmaz.backendproject2.dto.AppointmentDTO;
import com.volkankaytmaz.backendproject2.entity.Appointment;
import com.volkankaytmaz.backendproject2.services.AppointmentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Appointment")
public class AppointmentController {

    private final AppointmentServices appointmentServices;


    public AppointmentController(AppointmentServices appointmentServices) {
        this.appointmentServices = appointmentServices;
    }
    @RequestMapping("/findAll")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<AppointmentDTO> appointmentDTOS = appointmentServices.findAll();
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }
    @RequestMapping("/find/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(Long id) {
        AppointmentDTO appointmentDTO = appointmentServices.findById(id);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

}
