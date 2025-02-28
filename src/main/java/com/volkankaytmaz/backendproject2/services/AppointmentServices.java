package com.volkankaytmaz.backendproject2.services;

import com.volkankaytmaz.backendproject2.dto.AppointmentDTO;
import com.volkankaytmaz.backendproject2.entity.Appointment;
import com.volkankaytmaz.backendproject2.entity.Customer;
import com.volkankaytmaz.backendproject2.expection.ResourceNotFoundException;
import com.volkankaytmaz.backendproject2.mapper.AppointmentMapper;
import com.volkankaytmaz.backendproject2.repository.AppointmentRepository;
import com.volkankaytmaz.backendproject2.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServices {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServices(AppointmentRepository appointmentRepository,
                               CustomerRepository customerRepository,
                               AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<AppointmentDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }

    public AppointmentDTO findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment id: " + id + " Not Found"));
        return appointmentMapper.toAppointmentDTO(appointment);
    }

    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        if (appointmentDTO == null) {
            throw new IllegalArgumentException("Randevu bilgileri eksik, lütfen tekrar deneyin.");
        }

        Customer customer = customerRepository.findById(appointmentDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Müşteri bulunamadı: " + appointmentDTO.getCustomerId()));

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        appointment.setId(null); // ID'yi null olarak ayarlayarak yeni bir kayıt oluşturulmasını sağlıyoruz
        appointment.setCustomer(customer);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toAppointmentDTO(savedAppointment);
    }

    public AppointmentDTO update(Long id, AppointmentDTO appointmentDTO) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment id: " + id + " Not Found"));

        Customer customer = customerRepository.findById(appointmentDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Müşteri bulunamadı: " + appointmentDTO.getCustomerId()));

        Appointment updatedAppointment = appointmentMapper.toEntity(appointmentDTO);
        updatedAppointment.setId(id);
        updatedAppointment.setCustomer(customer);

        Appointment savedAppointment = appointmentRepository.save(updatedAppointment);
        return appointmentMapper.toAppointmentDTO(savedAppointment);
    }

    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment id: " + id + " Not Found");
        }
        appointmentRepository.deleteById(id);
    }

    public void deleteAll() {
        appointmentRepository.deleteAll();
    }
}

