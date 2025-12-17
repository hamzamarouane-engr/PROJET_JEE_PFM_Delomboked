package com.commercial.Commercial.Controller;

import com.commercial.Commercial.Dto.CommandeRequest;
import com.commercial.Commercial.Models.Produits_Prix;
import com.commercial.Commercial.Models.Tous_Commandes;
import com.commercial.Commercial.Service.CommandeService;
import com.commercial.Commercial.Service.ProduitService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Commercial Management (gestion_commercial)
 * This microservice manages product prices and all orders
 *
 * Base URL: /commercial
 * Port: 8082
 */
@RestController
@RequestMapping("/commercial")
@CrossOrigin(origins = "*")
public class CommercialController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CommercialController.class);

    private final ProduitService produitService;
    private final CommandeService commandeService;

    public CommercialController(ProduitService produitService, CommandeService commandeService) {
        this.produitService = produitService;
        this.commandeService = commandeService;
    }

    /**
     * GET /commercial/produits/all
     * Returns list of all products with their prices
     * Used by gestion_vente to display product information
     *
     * @return List of Produits_Prix
     */
    @GetMapping("/produits/all")
    public ResponseEntity<List<Produits_Prix>> getAllProduits() {
        log.info("Received request to get all products");
        List<Produits_Prix> produits = produitService.getAllProduits();
        log.info("Returning {} products", produits.size());
        return ResponseEntity.ok(produits);
    }

    /**
     * POST /commercial/commandes/add
     * Adds a new order to Tous_Commandes table
     * Called by gestion_vente after an order is placed
     *
     * @param request CommandeRequest with order details (codeCmd, client, codePdt,
     *                qteCmd, dateCmd)
     * @return Created Tous_Commandes entity
     */
    @PostMapping("/commandes/add")
    public ResponseEntity<Tous_Commandes> addCommande(@Valid @RequestBody CommandeRequest request) {
        log.info("Received request to add commande for client: {}", request.getClient());
        Tous_Commandes savedCommande = commandeService.addCommande(request);
        log.info("Commande added successfully with ID: {}", savedCommande.getCodeTousCmd());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommande);
    }

    /**
     * GET /commercial/commandes/all
     * Returns list of all orders
     * Additional endpoint for listing all orders
     *
     * @return List of Tous_Commandes
     */
    @GetMapping("/commandes/all")
    public ResponseEntity<List<Tous_Commandes>> getAllCommandes() {
        log.info("Received request to get all commandes");
        List<Tous_Commandes> commandes = commandeService.getAllCommandes();
        log.info("Returning {} commandes", commandes.size());
        return ResponseEntity.ok(commandes);
    }
}
