package com.volkankaytmaz.backendproject2.controller;

import com.volkankaytmaz.backendproject2.dto.CustomerDTO;
import com.volkankaytmaz.backendproject2.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServices customerServices;

    @Autowired
    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerServices.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerServices.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerServices.save(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Optional<CustomerDTO> updatedCustomer = customerServices.updateCustomer(id, customerDTO);
        return updatedCustomer
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerServices.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllCustomers() {
        customerServices.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

