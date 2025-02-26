package com.volkankaytmaz.backendproject2.Repository;

import com.volkankaytmaz.backendproject2.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
