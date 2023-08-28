package com.iamkhs.jwtauthentication.controller;

import com.iamkhs.jwtauthentication.jwtconfig.JwtRequest;
import com.iamkhs.jwtauthentication.jwtconfig.JwtResponse;
import com.iamkhs.jwtauthentication.jwtconfig.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller class for JWT token generation and authentication.

 * This class handles the generation of JWT tokens for user authentication and provides an endpoint
 * for generating tokens based on username and password.
 */
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class JwtController {

    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;


    /**
     * Generates a JWT token based on the provided username and password.
     *
     * @param request JwtRequest containing username and password
     * @return ResponseEntity containing the generated JWT token
     * @throws Exception if an error occurs during token generation or authentication
     */
    @PostMapping("/generate-token")
    public ResponseEntity<?> tokenGenerate(@RequestBody JwtRequest request) throws Exception {

        System.out.println(request);
        try{
            // Authenticate the user using the provided username and password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            // Load user details based on the authenticated username
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            // Generate a JWT token for the authenticated user
            String token = jwtUtil.generateToken(userDetails);

            // Return the generated JWT token in the response
            return ResponseEntity.ok(new JwtResponse(token));

        }catch (Exception e){

            // Throw an exception with an error message if authentication fails
            throw new Exception(e.getMessage());
        }
    }
}
