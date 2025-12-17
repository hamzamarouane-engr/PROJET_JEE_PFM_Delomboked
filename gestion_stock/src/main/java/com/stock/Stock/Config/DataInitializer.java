package com.stock.Stock.Config;

import com.stock.Stock.Models.Produits_Stock;
import com.stock.Stock.Repository.ProduitsStockRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data initializer to create sample stock on application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);

    private final ProduitsStockRepository produitsStockRepository;

    public DataInitializer(ProduitsStockRepository produitsStockRepository) {
        this.produitsStockRepository = produitsStockRepository;
    }

    @Override
    public void run(String... args) {

        if (produitsStockRepository.count() == 0) {

            Produits_Stock s1 = new Produits_Stock();
            s1.setCodePdt(0);
            s1.setQtePdt(50);
            s1.setPrixPdt(100);
            produitsStockRepository.save(s1);

            Produits_Stock s2 = new Produits_Stock();
            s2.setCodePdt(1);
            s2.setQtePdt(30);
            s2.setPrixPdt(120);
            produitsStockRepository.save(s2);

            Produits_Stock s3 = new Produits_Stock();
            s3.setCodePdt(2);
            s3.setQtePdt(25);
            s3.setPrixPdt(80);
            produitsStockRepository.save(s3);

            Produits_Stock s4 = new Produits_Stock();
            s4.setCodePdt(3);
            s4.setQtePdt(100);
            s4.setPrixPdt(150);
            produitsStockRepository.save(s4);

            log.info("✅ Stock + prix initialisés");
 
        } else {
            log.info("Stock already exists: {} entries found", produitsStockRepository.count());
        }
    }
}
