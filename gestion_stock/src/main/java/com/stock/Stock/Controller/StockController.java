package com.stock.Stock.Controller;

import com.stock.Stock.Models.Produits_Stock;
import com.stock.Stock.Service.StockService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Stock Management (gestion_stock)
 * This microservice manages product stock quantities
 *
 * Base URL: /stock
 * Port: 8083
 */
@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*")
public class StockController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StockController.class);

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * GET /stock/produits/all
     * Returns list of all products in stock
     * Used by gestion_vente to display available stock
     *
     * @return List of Produits_Stock
     */
    @GetMapping("/produits/all")
    public ResponseEntity<List<Produits_Stock>> getAllProductsStock() {
        log.info("Received request to get all products stock");
        List<Produits_Stock> stocks = stockService.getAllProductsStock();
        log.info("Returning {} products stock", stocks.size());
        return ResponseEntity.ok(stocks);
    }

    /**
     * PUT /stock/produits/subtract
     * Subtracts quantity from stock after a sale
     * Called by gestion_vente when an order is placed
     *
     * @param codePdt Product code
     * @param qteCmd  Quantity to subtract
     * @return Updated Produits_Stock
     */
    @PutMapping("/produits/subtract")
    public ResponseEntity<Produits_Stock> subtractStock(
            @RequestParam @NotNull(message = "Code produit is required") Integer codePdt,
            @RequestParam @NotNull(message = "Quantity is required") @Positive(message = "Quantity must be positive") Integer qteCmd) {

        log.info("Received request to subtract {} units from stock for product: {}", qteCmd, codePdt);

        try {
            Produits_Stock updatedStock = stockService.subtractStock(codePdt, qteCmd);
            log.info("Stock subtracted successfully. New quantity: {}", updatedStock.getQtePdt());
            return ResponseEntity.ok(updatedStock);
        } catch (IllegalArgumentException e) {
            log.error("Error subtracting stock: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /stock/produits/{codePdt}
     * Get stock information for a specific product
     *
     * @param codePdt Product code
     * @return Produits_Stock
     */
    @GetMapping("/produits/{codePdt}")
    public ResponseEntity<Produits_Stock> getStockByProductCode(@PathVariable Integer codePdt) {
        log.info("Received request to get stock for product code: {}", codePdt);

        try {
            Produits_Stock stock = stockService.getStockByProductCode(codePdt);
            return ResponseEntity.ok(stock);
        } catch (IllegalArgumentException e) {
            log.error("Product not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
