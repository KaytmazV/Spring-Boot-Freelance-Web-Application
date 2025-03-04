package com.volkankaytmaz.backendproject2.controller;

import com.volkankaytmaz.backendproject2.dto.AppointmentDTO;
import com.volkankaytmaz.backendproject2.service.AppointmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Appointment")  // Keep the same casing as in your URL
@CrossOrigin(origins = "*", maxAge = 3600)
public class AppointmentController {

    private final AppointmentServices appointmentServices;

    @Autowired
    public AppointmentController(AppointmentServices appointmentServices) {
        this.appointmentServices = appointmentServices;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<AppointmentDTO> appointmentDTOS = appointmentServices.findAll();
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO appointmentDTO = appointmentServices.findById(id);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO createdAppointment = appointmentServices.save(appointmentDTO);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO updatedAppointment = appointmentServices.update(id, appointmentDTO);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAppointments() {
        appointmentServices.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

