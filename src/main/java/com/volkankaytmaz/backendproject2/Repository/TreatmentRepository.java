package com.volkankaytmaz.backendproject2.Repository;

import com.volkankaytmaz.backendproject2.Entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
