package com.volkankaytmaz.backendproject2.service;

import com.volkankaytmaz.backendproject2.entity.User;
import com.volkankaytmaz.backendproject2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Validate required fields
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Kullanıcı adı gereklidir.");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Şifre gereklidir.");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new RuntimeException("E-posta adresi gereklidir.");
        }

        // Check if username or email already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Bu e-posta adresi zaten kullanılıyor.");
        }

        // Encode password and set default role
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

