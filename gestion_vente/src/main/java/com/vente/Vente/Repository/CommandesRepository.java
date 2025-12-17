package com.vente.Vente.Repository;

import com.vente.Vente.Models.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Commandes entity.
 * Provides CRUD operations and custom queries for order management.
 */
@Repository
public interface CommandesRepository extends JpaRepository<Commandes, Long> {

    /**
     * Find all orders by client name.
     * @param client Client name
     * @return List of orders
     */
    List<Commandes> findByClient(String client);

    /**
     * Find all orders by status.
     * @param status Order status (PENDING, COMPLETED, CANCELLED)
     * @return List of orders
     */
    List<Commandes> findByStatus(String status);

    /**
     * Find all orders by product code.
     * @param codePdt Product code
     * @return List of orders
     */
    List<Commandes> findByCodePdt(Integer codePdt);

    /**
     * Find all orders created by a specific user.
     * @param createdBy Username
     * @return List of orders
     */
    List<Commandes> findByCreatedBy(String createdBy);

    /**
     * Find all orders between two dates.
     * @param startDate Start date
     * @param endDate End date
     * @return List of orders
     */
    List<Commandes> findByDateCmdBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find all orders ordered by date descending.
     * @return List of orders
     */
    List<Commandes> findAllByOrderByDateCmdDesc();
}
