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

    // Ajouter un utilisateur à un centre de vaccination
    public Optional<CentreVaccination> addUserToCentreVaccination(Long idCentre, Long idUser);

    // Supprimer un utilisateur d'un centre de vaccination
    public Optional<CentreVaccination> deleteUserFromCentreVaccination(Long idCentre, Long idUser);

    // Obtenir tous les utilisateurs d'un centre de vaccination
    public List<CentreVaccination> findAllUsersFromCentreVaccination(Long idCentre);

    // Obtenir tous les utilisateurs en fonction de leur role et d'un centre de vaccination
    public List<CentreVaccination> findAllUsersByRoleAndCentreVaccination(String role, Long idCentre);


    
    public Optional<CentreVaccination> deleteById(Integer id);   // Supprimer un centre de vaccination par son id
}
