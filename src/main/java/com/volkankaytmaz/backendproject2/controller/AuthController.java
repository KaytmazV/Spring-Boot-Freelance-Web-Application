package com.volkankaytmaz.backendproject2.controller;

import com.volkankaytmaz.backendproject2.entity.RefreshToken;
import com.volkankaytmaz.backendproject2.entity.User;
import com.volkankaytmaz.backendproject2.security.JwtUtil;
import com.volkankaytmaz.backendproject2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.get("username"), loginRequest.get("password"))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication.getName());
        RefreshToken refreshToken = userService.createRefreshToken(authentication.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("refreshToken", refreshToken.getToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String requestRefreshToken = request.get("refreshToken");

        return userService.findByToken(requestRefreshToken)
                .map(userService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getEmail());
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token);
                    response.put("refreshToken", requestRefreshToken);
                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> signUpRequest) {
        User user = userService.registerUser(
                signUpRequest.get("email"),
                signUpRequest.get("name"),
                signUpRequest.get("password")
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", user.getId());
        return ResponseEntity.ok(response);
    }
}

