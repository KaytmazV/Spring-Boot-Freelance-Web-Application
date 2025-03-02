package com.volkankaytmaz.backendproject2.repository;

import com.volkankaytmaz.backendproject2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

