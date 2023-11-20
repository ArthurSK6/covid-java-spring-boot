package org.polytech.covidapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.CentreVaccination;

@Repository
public interface CentreVaccinationRepository extends JpaRepository<CentreVaccination, Long> {
    public List<CentreVaccination> findAll();   // Trouver tous les centres de vaccination
    public Optional<CentreVaccination> findById(Long id);   // Trouver un centre de vaccination par son id
    public List<CentreVaccination> findAllByCity(String city);  // Trouver tous les centres de vaccination par ville
    public List<CentreVaccination> findByPostalCode(String codePostal); // Trouver tous les centres de vaccination par code postal
    public Optional<CentreVaccination> findByName(String nom);  // Trouver un centre de vaccination par son nom

    public void deleteById(Integer id);   // Supprimer un centre de vaccination par son id
}
