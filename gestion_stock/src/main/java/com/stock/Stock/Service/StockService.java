package com.stock.Stock.Service;

import com.stock.Stock.Models.Produits_Stock;
import com.stock.Stock.Repository.ProduitsStockRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for Stock Management
 * Contains business logic for stock operations
 */
@Service
public class StockService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StockService.class);

    private final ProduitsStockRepository produitsStockRepository;

    public StockService(ProduitsStockRepository produitsStockRepository) {
        this.produitsStockRepository = produitsStockRepository;
    }

    /**
     * Get all products in stock
     * 
     * @return List of all Produits_Stock
     */
    public List<Produits_Stock> getAllProductsStock() {
        log.info("Fetching all products from stock");
        List<Produits_Stock> products = produitsStockRepository.findAll();
        log.info("Found {} products in stock", products.size());
        return products;
    }

    /**
     * Subtract quantity from stock
     * Used when gestion_vente makes a sale
     *
     * @param codePdt Product code
     * @param qteCmd  Quantity to subtract
     * @return Updated Produits_Stock
     * @throws IllegalArgumentException if product not found or insufficient stock
     */
    @Transactional
    public Produits_Stock subtractStock(Integer codePdt, Integer qteCmd) {
        log.info("Subtracting {} units from stock for product code: {}", qteCmd, codePdt);

        // Find product in stock
        Produits_Stock stock = produitsStockRepository.findByCodePdt(codePdt)
                .orElseThrow(() -> {
                    log.error("Product with code {} not found in stock", codePdt);
                    return new IllegalArgumentException("Product not found in stock: " + codePdt);
                });

        // Check if enough quantity available
        if (stock.getQtePdt() < qteCmd) {
            log.error("Insufficient stock for product {}. Available: {}, Requested: {}",
                    codePdt, stock.getQtePdt(), qteCmd);
            throw new IllegalArgumentException(
                    String.format("Insufficient stock. Available: %d, Requested: %d",
                            stock.getQtePdt(), qteCmd));
        }

        // Subtract quantity
        int newQuantity = stock.getQtePdt() - qteCmd;
        stock.setQtePdt(newQuantity);

        Produits_Stock updated = produitsStockRepository.save(stock);
        log.info("Stock updated successfully. Product: {}, New quantity: {}", codePdt, newQuantity);

        return updated;
    }

    /**
     * Get stock information for a specific product
     * 
     * @param codePdt Product code
     * @return Produits_Stock
     */
    public Produits_Stock getStockByProductCode(Integer codePdt) {
        log.info("Fetching stock for product code: {}", codePdt);
        return produitsStockRepository.findByCodePdt(codePdt)
                .orElseThrow(() -> {
                    log.error("Product with code {} not found", codePdt);
                    return new IllegalArgumentException("Product not found: " + codePdt);
                });
    }
}
