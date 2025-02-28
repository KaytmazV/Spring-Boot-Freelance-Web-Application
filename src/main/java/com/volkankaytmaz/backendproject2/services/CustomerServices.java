package com.volkankaytmaz.backendproject2.services;

import com.volkankaytmaz.backendproject2.dto.CustomerDTO;
import com.volkankaytmaz.backendproject2.dto.CustomerWithAppointmentsDTO;
import com.volkankaytmaz.backendproject2.entity.Customer;
import com.volkankaytmaz.backendproject2.expection.ResourceNotFoundException;
import com.volkankaytmaz.backendproject2.mapper.CustomerMapper;
import com.volkankaytmaz.backendproject2.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServices {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServices(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toDTOList(customers);
    }

    public CustomerDTO findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id bulunamadı: " + id));
        return customerMapper.toDTO(customer);
    }

    public CustomerWithAppointmentsDTO findByIdWithAppointments(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id bulunamadı: " + id));
        return customerMapper.toDTOWithAppointments(customer);
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        if (customerDTO == null || customerDTO.getName() == null) {
            throw new IllegalArgumentException("Müşteri bilgileri eksik, lütfen tekrar deneyin.");
        }
        if (customerDTO.getPhone() == null) {
            throw new IllegalArgumentException("Telefon numarası eksik, lütfen tekrar deneyin.");
        }
        try {
            Customer customer = customerMapper.toEntity(customerDTO);
            Customer savedCustomer = customerRepository.save(customer);
            return customerMapper.toDTO(savedCustomer);
        } catch (Exception e) {
            throw new RuntimeException("Müşteri oluşturulurken bir hata oluştu: " + e.getMessage());
        }
    }

    public Optional<CustomerDTO> updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            return Optional.empty();
        }
        try {
            Customer customer = customerMapper.toEntity(customerDTO);
            customer.setId(id); // Burada artık Long türünde id kullanılıyor
            Customer updatedCustomer = customerRepository.save(customer);
            return Optional.of(customerMapper.toDTO(updatedCustomer));
        } catch (Exception e) {
            throw new RuntimeException("Müşteri güncellenirken bir hata oluştu: " + e.getMessage());
        }
    }

    public void deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Müşteri silinirken bir hata oluştu: " + e.getMessage());
        }
    }

    public void deleteAll() {
        try {
            customerRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Müşteriler silinirken bir hata oluştu: " + e.getMessage());
        }
    }
}

