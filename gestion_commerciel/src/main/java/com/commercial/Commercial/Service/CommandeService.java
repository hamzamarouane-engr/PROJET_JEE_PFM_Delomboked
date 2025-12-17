package com.commercial.Commercial.Service;

import com.commercial.Commercial.Dto.CommandeRequest;
import com.commercial.Commercial.Models.Tous_Commandes;
import com.commercial.Commercial.Repository.TousCommandesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CommandeService.class);

    private final TousCommandesRepository tousCommandesRepository;

    public CommandeService(TousCommandesRepository tousCommandesRepository) {
        this.tousCommandesRepository = tousCommandesRepository;
    }

    public List<Tous_Commandes> getAllCommandes() {
        log.info("Fetching all orders");
        return tousCommandesRepository.findAll();
    }

    public Tous_Commandes addCommande(CommandeRequest request) {
        log.info("Creating new order for client: {}", request.getClient());

        Tous_Commandes commande = new Tous_Commandes(
                null, // codeTousCmd will be auto-generated
                request.getCodeCmd(),
                request.getClient(),
                request.getCodePdt(),
                request.getQteCmd(),
                request.getDateCmd());

        Tous_Commandes saved = tousCommandesRepository.save(commande);
        log.info("Order created successfully with id: {}", saved.getCodeTousCmd());
        return saved;
    }
}
