package org.polytech.covidapi.repository;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Long> {

    // Trouver tous les centres de vaccination par ville
    public List<VaccinationCenter> findAllByCity(String city);

    // Trouver tous les centres de vaccination par code postal
    public List<VaccinationCenter> findAllByPostalCode(String codePostal);
    
    // Trouver un centre de vaccination par son nom
    public Optional<VaccinationCenter> findByName(String name);
}
