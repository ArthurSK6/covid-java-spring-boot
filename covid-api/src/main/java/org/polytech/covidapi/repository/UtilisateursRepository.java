package org.polytech.covidapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.Utilisateurs;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Integer> {

    
} 

