package com.volkankaytmaz.backendproject2.Repository;

import com.volkankaytmaz.backendproject2.Entity.CustomerTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTreatmentRepository extends JpaRepository<CustomerTreatment, Long> {
}
