package com.volkankaytmaz.backendproject2.Services;

import com.volkankaytmaz.backendproject2.Entity.Customer;
import com.volkankaytmaz.backendproject2.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all customers");
        }
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Söz konusu " + id + " hatalı."));

    }

    public Customer saveCustomer(Customer customer) {
        if (customer.getId() == null || customer.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Müşteri bilgileri eksik, lütfen tekrar deneyin.");
        }
        if (customer.getName() == null) {
            throw new IllegalArgumentException(("Müşteri ismi eksik, lütfen tekrar deneyin"));
        }
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Error save customer " + customer.getPhoneNumber());
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

}
