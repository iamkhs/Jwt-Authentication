package com.iamkhs.jwtauthentication.config;

import com.iamkhs.jwtauthentication.entities.User;
import com.iamkhs.jwtauthentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of UserDetailsService to load user details from the database.

 * This service class retrieves user details from the database based on the provided username.
 * It is used by the Spring Security framework for authenticating and authorizing users.
 */

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * Loads user details from the database based on the provided username.
     *
     * @param username The username of the user to retrieve details for
     * @return UserDetailsImpl instance containing user details
     * @throws UsernameNotFoundException if the user is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user in the database by their username
        User user = userRepository.findByUsername(username);

        // If the user is found, create and return a UserDetailsImpl instance
        if (user != null){
            return new UserDetailsImpl(user);
        }
        // If the user is not found, throw an exception
        throw new UsernameNotFoundException(username+ " User not FOUND!");
    }
}
