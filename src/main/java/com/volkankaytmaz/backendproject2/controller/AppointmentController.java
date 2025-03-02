package com.volkankaytmaz.backendproject2.controller;

import com.volkankaytmaz.backendproject2.dto.AppointmentDTO;
import com.volkankaytmaz.backendproject2.expection.ResourceNotFoundException;
import com.volkankaytmaz.backendproject2.service.AppointmentServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
    private final AppointmentServices appointmentServices;

    @Autowired
    public AppointmentController(AppointmentServices appointmentServices) {
        this.appointmentServices = appointmentServices;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments(Authentication authentication) {
        logger.info("Fetching all appointments for user: {}", authentication.getName());
        try {
            List<AppointmentDTO> appointmentDTOS = appointmentServices.findAll();
            return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching appointments: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id, Authentication authentication) {
        logger.info("Fetching appointment with id: {} for user: {}", id, authentication.getName());
        try {
            AppointmentDTO appointmentDTO = appointmentServices.findById(id);
            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching appointment: {}", e.getMessage());
            return new ResponseEntity<>("Error fetching appointment: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointmentDTO, Authentication authentication) {
        logger.info("Attempting to create appointment for user: {}", authentication.getName());
        try {
            AppointmentDTO createdAppointment = appointmentServices.save(appointmentDTO);
            logger.info("Appointment created successfully");
            return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            logger.error("Error creating appointment: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error creating appointment: {}", e.getMessage());
            return new ResponseEntity<>("Error creating appointment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO, Authentication authentication) {
        logger.info("Attempting to update appointment with id: {} for user: {}", id, authentication.getName());
        try {
            AppointmentDTO updatedAppointment = appointmentServices.update(id, appointmentDTO);
            logger.info("Appointment updated successfully");
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating appointment: {}", e.getMessage());
            return new ResponseEntity<>("Error updating appointment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id, Authentication authentication) {
        logger.info("Attempting to delete appointment with id: {} for user: {}", id, authentication.getName());
        try {
            appointmentServices.delete(id);
            logger.info("Appointment deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting appointment: {}", e.getMessage());
            return new ResponseEntity<>("Error deleting appointment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllAppointments(Authentication authentication) {
        logger.info("Attempting to delete all appointments for user: {}", authentication.getName());
        try {
            appointmentServices.deleteAll();
            logger.info("All appointments deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting all appointments: {}", e.getMessage());
            return new ResponseEntity<>("Error deleting all appointments: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

