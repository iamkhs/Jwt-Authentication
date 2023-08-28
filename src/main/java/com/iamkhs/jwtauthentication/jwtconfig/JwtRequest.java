package com.iamkhs.jwtauthentication.jwtconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Represents a JWT (JSON Web Token) authentication request.

 * This class encapsulates the username and password provided during authentication.
 */

@Data
@AllArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
}
