package com.volkankaytmaz.backendproject2.service;

import com.volkankaytmaz.backendproject2.dto.ServiceDTO;
import com.volkankaytmaz.backendproject2.entity.Service;
import com.volkankaytmaz.backendproject2.mapper.ServiceMapper;
import com.volkankaytmaz.backendproject2.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceManagementService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceManagementService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServiceDTO getServiceById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
    }

    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);
        Service savedService = serviceRepository.save(service);
        return serviceMapper.toDTO(savedService);
    }

    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Service not found with id: " + id);
        }
        Service service = serviceMapper.toEntity(serviceDTO);
        service.setId(id);
        Service updatedService = serviceRepository.save(service);
        return serviceMapper.toDTO(updatedService);
    }

    public void deleteService(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Service not found with id: " + id);
        }
        serviceRepository.deleteById(id);
    }
}

