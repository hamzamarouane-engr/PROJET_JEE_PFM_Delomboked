package com.vente.Vente.Repository;

import com.vente.Vente.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Users entity.
 * Provides CRUD operations and custom queries for user management.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Find a user by username.
     * @param username Username
     * @return Optional containing the user if found
     */
    Optional<Users> findByUsername(String username);

    /**
     * Check if a user exists by username.
     * @param username Username
     * @return True if exists, false otherwise
     */
    Boolean existsByUsername(String username);

    /**
     * Find a user by email.
     * @param email Email address
     * @return Optional containing the user if found
     */
    Optional<Users> findByEmail(String email);
}
