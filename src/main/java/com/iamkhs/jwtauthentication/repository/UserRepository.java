package com.iamkhs.jwtauthentication.repository;

import com.iamkhs.jwtauthentication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for managing User entities.

 * This interface provides methods for accessing and managing user data in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
