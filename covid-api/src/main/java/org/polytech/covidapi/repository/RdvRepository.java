package org.polytech.covidapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.Rdv;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Long> {

    // Trouver tous les rendez-vous d'une journée
    public List<Rdv> findAllByDate(Date date);

}
