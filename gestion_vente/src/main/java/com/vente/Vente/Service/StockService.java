package com.vente.Vente.Service;

import com.vente.Vente.Dto.StockDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);

    private final WebClient webClient;

    @Value("${microservice.stock.url}")
    private String stockServiceUrl;

    public StockService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public StockDTO getStockByCodePdt(Integer codePdt) {
        return webClient.get()
                .uri(stockServiceUrl + "/produits/" + codePdt)
                .retrieve()
                .bodyToMono(StockDTO.class)
                .block();
    }

    public void decreaseStock(Integer codePdt, Integer quantity) {
        webClient.put() // Changed to PUT as per controller
                .uri(stockServiceUrl + "/produits/subtract?codePdt=" + codePdt + "&qteCmd=" + quantity)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
