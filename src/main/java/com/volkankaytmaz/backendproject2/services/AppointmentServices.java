package com.volkankaytmaz.backendproject2.services;

import com.volkankaytmaz.backendproject2.dto.AppointmentDTO;
import com.volkankaytmaz.backendproject2.entity.Appointment;
import com.volkankaytmaz.backendproject2.expection.ResourceNotFoundException;
import com.volkankaytmaz.backendproject2.mapper.AppointmentMapper;
import com.volkankaytmaz.backendproject2.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServices {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServices(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<AppointmentDTO> findAll() {
        try {
            List<Appointment> appointments = appointmentRepository.findAll();
            return appointments.stream()
                    .map(appointmentMapper::toAppointmentDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Randevu alınırken bir hata oluştu: " + e.getMessage());
        }
    }

    public AppointmentDTO findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment id: " + id + " Not Found"));
        return appointmentMapper.toAppointmentDTO(appointment);
    }

    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        if (appointmentDTO.getId() != null) {
            throw new IllegalArgumentException("Randevu bilgileri eksik, lütfen tekrar deneyin.");
        }
        if (appointmentDTO.getCustomer() == null) {
            throw new IllegalArgumentException("Randevu bilgileri eksik, lütfen tekrar deneyin.");
        }
        try {
            Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
            appointment = appointmentRepository.save(appointment);
            return appointmentMapper.toAppointmentDTO(appointment);
        } catch (Exception e) {
            throw new RuntimeException("Randevu oluşturulurken bir hata oluştu: " + e.getMessage());
        }
    }

    public Optional<AppointmentDTO> update(Long id, AppointmentDTO appointmentDTO) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isEmpty()) {
            return Optional.empty();
        }
        try {
            Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
            appointment.setId(id);
            Appointment updatedAppointment = appointmentRepository.save(appointment);
            return Optional.of(appointmentMapper.toAppointmentDTO(updatedAppointment));
        } catch (Exception e) {
            throw new RuntimeException("Randevu güncellenirken bir hata oluştu: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Randevu silinirken bir hata oluştu: " + e.getMessage());

        }
    }

    public void deleteAll(){
        try {
            appointmentRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Müşteriler silinirken bir hata oluştu: " + e.getMessage());
        }
    }
}

