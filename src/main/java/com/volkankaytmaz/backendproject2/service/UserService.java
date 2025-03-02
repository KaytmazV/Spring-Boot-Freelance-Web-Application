package com.volkankaytmaz.backendproject2.service;

import com.volkankaytmaz.backendproject2.entity.User;
import com.volkankaytmaz.backendproject2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Diğer metodlar...

    public User processOAuthPostLogin(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        User existUser = userRepository.findByEmail(email);

        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setUsername(email);
            newUser.setAuthProvider("GOOGLE");
            newUser.setRole("USER");
            return userRepository.save(newUser);
        }

        return existUser;
    }
}

