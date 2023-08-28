package com.iamkhs.jwtauthentication.jwtconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a JWT (JSON Web Token) response.

 * This class encapsulates a JWT token and provides a simple way to package it as a response.
 */
@AllArgsConstructor
@Getter
public class JwtResponse{
    private String token;
}
