package com.iamkhs.jwtauthentication.jwtconfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * Custom JWT authentication filter.

 * This class is responsible for filtering and processing JWT authentication in incoming requests.
 */
@Configuration
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extracting the JWT token and username from the request header
        String token;
        String username;
        String requestHeaderWithToken = request.getHeader("Authorization");
        if (requestHeaderWithToken != null && requestHeaderWithToken.startsWith("Bearer ")) {
            token = requestHeaderWithToken.substring(7);

            try {
                // Extracting user details based on the extracted username from JWT
                username = jwtUtil.extractUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Validating the JWT token against the user details
                jwtUtil.validateToken(token, userDetails);

                // If the user is authenticated and not already in the SecurityContextHolder, set the authentication
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e) {
                // In case of an exception (invalid token, user not found, etc.), just print the stack trace
                e.getStackTrace();
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
