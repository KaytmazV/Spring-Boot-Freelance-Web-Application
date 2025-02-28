package com.volkankaytmaz.backendproject2.repository;

import com.volkankaytmaz.backendproject2.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}

