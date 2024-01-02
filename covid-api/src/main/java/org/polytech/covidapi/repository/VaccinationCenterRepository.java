package org.polytech.covidapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.polytech.covidapi.domain.VaccinationCenter;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Long> {
    
    // Trouver tous les centres de vaccination
    //public List<VaccinationCenter> findAll();

    // Trouver tous les centres de vaccination par ville
    public List<VaccinationCenter> findAllByCity(String city);

    // Trouver tous les centres de vaccination par code postal
    public List<VaccinationCenter> findAllByPostalCode(String codePostal);
    
    // Trouver un centre de vaccination par son nom
    public Optional<VaccinationCenter> findByName(String name);

    // Ajouter un utilisateur à un centre de vaccination
    //public Optional<VaccinationCenter> addUserToVaccinationCenter(Long id, String name);

    // Lier un utilisateur à un centre de vaccination
    // public void linkUserToVaccinationCenter(Long userId, String centerName);

    // Supprimer un centre de vaccination par son Nom
    public Long deleteByName(String name);
}
