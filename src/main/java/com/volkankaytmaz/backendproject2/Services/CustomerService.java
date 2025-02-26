package com.volkankaytmaz.backendproject2.Services;

import com.volkankaytmaz.backendproject2.Entity.Customer;
import com.volkankaytmaz.backendproject2.Repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            logger.error("Tüm müşteriler bulunurken hata oluştu", e);
            throw new RuntimeException("Tüm müşteriler bulunurken hata oluştu");
        }
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Söz konusu " + id + " hatalı."));

    }

    public Customer saveCustomer(@Valid Customer customer) {
        validateCustomer(customer);
        try {
            Customer savedCustomer = customerRepository.save(customer);
            logger.info("Müşteri kaydedildi : {}", savedCustomer.getId());
            return savedCustomer;
        } catch (Exception e) {
            logger.error("Müşteri kaydedilirken hata oluştu", e);
            throw new RuntimeException("Error save customer " + e.getMessage());
        }
    }

    public Optional<Customer> updateCustomer(Customer customer) {
        Optional<Customer> customerEntity = customerRepository.findById(customer.getId());
        if (customerEntity.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(customerRepository.save(customer));
        } catch (Exception e) {
            throw new RuntimeException("Müşteri güncellenirken bir hata oluştu: " + e.getMessage());
        }
    }
    public String deleteAllCustomer() {
        try {
            customerRepository.deleteAll();
            return "Müteriler silindi";
        } catch (Exception e) {
            throw new RuntimeException("Error delete customer ");
        }
    }
    public String deleteCustomerId(Long id) {
        try {
            customerRepository.deleteById(id);
        return "müşteri silindi";
    } catch (Exception e) {
        throw new RuntimeException("Error delete customer id " + id);}
    }


    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Müşteri ismi boş olamaz.");
        }
        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Müşteri telefon numarası boş olamaz.");
        }
    }

}
