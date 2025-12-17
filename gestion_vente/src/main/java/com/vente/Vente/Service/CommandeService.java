package com.vente.Vente.Service;

import com.vente.Vente.Dto.CommandeRequest;
import com.vente.Vente.Dto.CommandeResponse;
import com.vente.Vente.Dto.ProduitDTO;
import com.vente.Vente.Dto.StockDTO;
import com.vente.Vente.Models.Commandes;
import com.vente.Vente.Repository.CommandesRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService {

    private static final Logger log = LoggerFactory.getLogger(CommandeService.class);

    private final CommandesRepository commandesRepository;
    private final CommercialService commercialService;
    private final StockService stockService;

    public CommandeService(CommandesRepository commandesRepository,
            CommercialService commercialService,
            StockService stockService) {
        this.commandesRepository = commandesRepository;
        this.commercialService = commercialService;
        this.stockService = stockService;
    }

    public List<CommandeResponse> getAllCommandes() {
        return commandesRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CommandeResponse getCommandeById(Long id) {
        Commandes cmd = commandesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        return mapToResponse(cmd);
    }

    public CommandeResponse createCommande(CommandeRequest request) {

        ProduitDTO produit = commercialService.getProduitByCode(request.getCodePdt());
        StockDTO stock = stockService.getStockByCodePdt(request.getCodePdt());

        if (stock.getQtePdt() < request.getQteCmd()) {
            throw new IllegalStateException("Stock insuffisant");
        }

        Commandes cmd = new Commandes();
        cmd.setCodePdt(request.getCodePdt());
        cmd.setQteCmd(request.getQteCmd());
        cmd.setPrixUnitaire(produit.getPrixPdt());
        cmd.setMontantTotal(produit.getPrixPdt() * request.getQteCmd());
        cmd.setClient(request.getClient());
        cmd.setStatus("CREATED");
        cmd.setDateCmd(LocalDateTime.now());

        commandesRepository.save(cmd);

        stockService.decreaseStock(request.getCodePdt(), request.getQteCmd());

        // Archive order in Commercial Service
        commercialService.addCommande(cmd);

        return mapToResponse(cmd);
    }

    private CommandeResponse mapToResponse(Commandes cmd) {

        CommandeResponse res = new CommandeResponse();
        res.setCodeCmd(cmd.getCodeCmd());
        res.setCodePdt(cmd.getCodePdt());
        res.setNomPdt(cmd.getNomPdt());
        res.setQteCmd(cmd.getQteCmd());
        res.setPrixUnitaire(cmd.getPrixUnitaire());
        res.setMontantTotal(cmd.getMontantTotal());
        res.setClient(cmd.getClient());
        res.setStatus(cmd.getStatus());
        res.setDateCmd(cmd.getDateCmd());

        return res;
    }
}