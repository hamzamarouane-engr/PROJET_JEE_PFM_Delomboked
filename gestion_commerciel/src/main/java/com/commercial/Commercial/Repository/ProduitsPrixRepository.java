package com.commercial.Commercial.Repository;

import com.commercial.Commercial.Models.Produits_Prix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitsPrixRepository extends JpaRepository<Produits_Prix, Integer> {
}