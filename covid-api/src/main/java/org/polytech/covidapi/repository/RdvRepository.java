package org.polytech.covidapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.Rdv;
import org.polytech.covidapi.domain.VaccinationCenter;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Long> {

    // Trouver tous les rendez-vous d'une journée en fonction du centre de vaccination
    public List<Rdv> findAllByDateAndVaccinationCenter(Date date, VaccinationCenter vaccinationCenter);

}
