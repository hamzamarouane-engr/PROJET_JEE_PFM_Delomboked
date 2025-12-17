package com.commercial.Commercial.Repository;

import com.commercial.Commercial.Models.Tous_Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TousCommandesRepository extends JpaRepository<Tous_Commandes, Integer> {
}