package com.iamkhs.jwtauthentication.jwtconfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * Custom authentication entry point for JWT-based authentication.
 * This component is responsible for handling unauthorized access attempts.
 * When an unauthorized request is detected, it sends an HTTP 401 Unauthorized response.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        // Send an HTTP 401 Unauthorized response for unauthorized access attempts
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
