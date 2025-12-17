package com.stock.Stock.Repository;

import com.stock.Stock.Models.Produits_Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Produits_Stock entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface ProduitsStockRepository extends JpaRepository<Produits_Stock, Integer> {

    /**
     * Find product stock by product code
     * @param codePdt Product code
     * @return Optional of Produits_Stock
     */
    Optional<Produits_Stock> findByCodePdt(Integer codePdt);
}
