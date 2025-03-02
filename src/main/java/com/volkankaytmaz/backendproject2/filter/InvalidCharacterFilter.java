package com.volkankaytmaz.backendproject2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InvalidCharacterFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(InvalidCharacterFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (containsInvalidCharacters(requestURI)) {
            logger.warn("Invalid request detected: {}", requestURI);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid characters in request");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean containsInvalidCharacters(String input) {
        return !input.matches("^[a-zA-Z0-9./_-]*$");
    }
}

