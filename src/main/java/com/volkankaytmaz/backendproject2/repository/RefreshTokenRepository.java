package com.volkankaytmaz.backendproject2.repository;

import com.volkankaytmaz.backendproject2.entity.RefreshToken;
import com.volkankaytmaz.backendproject2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}

