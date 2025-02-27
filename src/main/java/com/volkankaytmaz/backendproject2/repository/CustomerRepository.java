package com.volkankaytmaz.backendproject2.repository;

import com.volkankaytmaz.backendproject2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
