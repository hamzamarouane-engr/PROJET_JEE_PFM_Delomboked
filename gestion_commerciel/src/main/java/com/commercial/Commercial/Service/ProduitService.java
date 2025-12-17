package com.commercial.Commercial.Service;

import com.commercial.Commercial.Models.Produits_Prix;
import com.commercial.Commercial.Repository.ProduitsPrixRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProduitService.class);

    private final ProduitsPrixRepository produitsPrixRepository;

    public ProduitService(ProduitsPrixRepository produitsPrixRepository) {
        this.produitsPrixRepository = produitsPrixRepository;
    }

    public List<Produits_Prix> getAllProduits() {
        log.info("Fetching all products");
        return produitsPrixRepository.findAll();
    }

    public Optional<Produits_Prix> findByCode(Integer codePdt) {
        log.info("Fetching product with code: {}", codePdt);
        return produitsPrixRepository.findById(codePdt);
    }
}
