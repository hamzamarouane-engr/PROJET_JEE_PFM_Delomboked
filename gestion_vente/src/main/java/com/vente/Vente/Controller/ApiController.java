package com.vente.Vente.Controller;

import com.vente.Vente.Service.CommandeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commandes")
public class ApiController {

    private static final Logger log =
            LoggerFactory.getLogger(ApiController.class);

    private final CommandeService commandeService;

    public ApiController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public Object getAll() {
        log.info("Fetching all commandes (API)");
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id) {
        log.info("Fetching commande {}", id);
        return commandeService.getCommandeById(id);
    }
}
