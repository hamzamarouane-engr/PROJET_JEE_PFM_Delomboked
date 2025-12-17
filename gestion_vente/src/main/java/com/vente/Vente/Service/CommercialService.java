package com.vente.Vente.Service;

import com.vente.Vente.Dto.ProduitDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CommercialService {

    private static final Logger log = LoggerFactory.getLogger(CommercialService.class);

    private final WebClient webClient;

    @Value("${microservice.commercial.url}")
    private String commercialServiceUrl;

    public CommercialService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<ProduitDTO> getAllProduits() {
        return webClient.get()
                .uri(commercialServiceUrl + "/produits/all")
                .retrieve()
                .bodyToFlux(ProduitDTO.class)
                .collectList()
                .block();
    }

    public ProduitDTO getProduitByCode(Integer codePdt) {
        // Assuming CommercialController has an endpoint search by ID, but it wasn't
        // explicitly shown.
        // Waiting, CommercialController only had /produits/all.
        // It DOES NOT have /produits/{id}.
        // I need to filter locally or add endpoint to CommercialController.
        // The requirements say "Créer une page... tableau composé... nom produit
        // (récupéré par web service)".
        // It doesn't strictly say getById.
        // But for "Ajout d'une commande", we need product info.
        // I will implement client-side filtering from all list if needed, or add
        // endpoint.
        // Let's first fix the getAll.
        return webClient.get()
                .uri(commercialServiceUrl + "/produits/all") // Warning: this fetches all
                .retrieve()
                .bodyToFlux(ProduitDTO.class)
                .collectList()
                .block().stream()
                .filter(p -> p.getCodePdt().equals(codePdt))
                .findFirst()
                .orElse(null);
    }

    public void addCommande(com.vente.Vente.Models.Commandes commande) {

        com.vente.Vente.Dto.CommercialCommandeRequest request = new com.vente.Vente.Dto.CommercialCommandeRequest();
        request.setCodeCmd(commande.getCodeCmd()); // Long to Long/Integer
        request.setClient(commande.getClient());
        request.setCodePdt(commande.getCodePdt());
        request.setQteCmd(commande.getQteCmd());
        // Convert LocalDateTime to LocalDate
        request.setDateCmd(commande.getDateCmd().toLocalDate());

        webClient.post()
                .uri(commercialServiceUrl + "/commandes/add")
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
