package com.volkankaytmaz.backendproject2.security;

import com.volkankaytmaz.backendproject2.entity.User;
import com.volkankaytmaz.backendproject2.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public OAuth2LoginSuccessHandler(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        User user = userService.processOAuthPostLogin(oAuth2User);

        String token = jwtUtil.generateToken(user.getEmail());

        // Add token to response header
        response.addHeader("Authorization", "Bearer " + token);

        // Redirect to home page or a specific endpoint
        getRedirectStrategy().sendRedirect(request, response, "/api/public/home");
    }
}

