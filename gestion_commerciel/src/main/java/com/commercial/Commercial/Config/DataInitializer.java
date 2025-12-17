package com.commercial.Commercial.Config;

import com.commercial.Commercial.Models.Produits_Prix;
import com.commercial.Commercial.Repository.ProduitsPrixRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data initializer to create sample products on application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);

    private final ProduitsPrixRepository produitsPrixRepository;

    public DataInitializer(ProduitsPrixRepository produitsPrixRepository) {
        this.produitsPrixRepository = produitsPrixRepository;
    }

    @Override
    public void run(String... args) {
        log.info("Checking sample products...");

        if (produitsPrixRepository.count() == 0) {
            log.info("No products found. Creating sample products...");

            // Product 1
            Produits_Prix p1 = new Produits_Prix();
            p1.setCodePdt(1);
            p1.setNomPdt("ASUS ROG Strix");
            p1.setDescPdt("Laptop gaming haute performance");
            p1.setPrixPdt(15000);
            produitsPrixRepository.save(p1);

            // Product 2
            Produits_Prix p2 = new Produits_Prix();
            p2.setCodePdt(2);
            p2.setNomPdt("ASUS ZenBook");
            p2.setDescPdt("Ultrabook leger et elegant");
            p2.setPrixPdt(8500);
            produitsPrixRepository.save(p2);

            // Product 3
            Produits_Prix p3 = new Produits_Prix();
            p3.setCodePdt(3);
            p3.setNomPdt("ASUS TUF Gaming");
            p3.setDescPdt("Laptop gaming robuste");
            p3.setPrixPdt(12000);
            produitsPrixRepository.save(p3);

            // Product 4
            Produits_Prix p4 = new Produits_Prix();
            p4.setCodePdt(4);
            p4.setNomPdt("ASUS VivoBook");
            p4.setDescPdt("Laptop polyvalent pour tous");
            p4.setPrixPdt(6000);
            produitsPrixRepository.save(p4);

            // Product 5
            Produits_Prix p5 = new Produits_Prix();
            p5.setCodePdt(5);
            p5.setNomPdt("ASUS ProArt");
            p5.setDescPdt("Station de travail pour createurs");
            p5.setPrixPdt(20000);
            produitsPrixRepository.save(p5);

            log.info("✅ 5 sample products created!");
        } else {
            log.info("Products already exist: {} products found", produitsPrixRepository.count());
        }
    }
}
