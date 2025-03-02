package com.volkankaytmaz.backendproject2.service;

import com.volkankaytmaz.backendproject2.entity.User;
import com.volkankaytmaz.backendproject2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User processOAuthPostLogin(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        User existUser = userRepository.findByEmail(email);

        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setAuthProvider("GOOGLE");
            newUser.setRole("USER"); // Default role for new users
            newUser.setPassword(passwordEncoder.encode("changeme")); // Set a default password
            return userRepository.save(newUser);
        }

        return existUser;
    }

    public User registerUser(String email, String name, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("USER");
        newUser.setAuthProvider("LOCAL");
        return userRepository.save(newUser);
    }
}

