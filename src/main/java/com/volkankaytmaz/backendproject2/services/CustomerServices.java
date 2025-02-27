package com.volkankaytmaz.backendproject2.services;


import com.volkankaytmaz.backendproject2.entity.Customer;
import com.volkankaytmaz.backendproject2.expection.ResourceNotFoundException;
import com.volkankaytmaz.backendproject2.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServices {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
       try {
           return customerRepository.findAll();
       } catch (Exception e) {
           throw new RuntimeException("Müşteriler alınırken bir hata oluştu: " + e.getMessage());
       }
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id bulunamadı: " + id));
    }

    public Customer save(Customer customer) {
        if (customer == null || customer.getName() == null) {
            throw new IllegalArgumentException("Müşteri bilgileri eksik, lütfen tekrar deneyin.");
        }
        if (customer.getPhone() == null ){
            throw new IllegalArgumentException("Telefon numarası eksik, lütfen tekrar deneyin.");
        }
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Müşteri oluşturulurken bir hata oluştu: " + e.getMessage());
        }
    }

    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(customerRepository.save(customer));

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
            throw new RuntimeException("Müşteriler bir hata oluştu: " + e.getMessage());
        }
    }

}
