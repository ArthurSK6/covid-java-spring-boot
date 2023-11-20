package org.polytech.covidapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.polytech.covidapi.domain.CentreVaccination;
import org.polytech.covidapi.repository.CentreVaccinationRepository;

@Service
public class CentreVaccinationService {
    @Autowired
    private CentreVaccinationRepository centerRepository;

    // Trouver tous les centres de vaccination
    public List<CentreVaccination> findAll() {
        return centerRepository.findAll();
    }

    // Trouver un centre de vaccination par son id
    public Optional<CentreVaccination> findById(Long id) {
        return  centerRepository.findById(id);
    }

    // Trouver tous les centres de vaccination par ville
    public List<CentreVaccination> findAllByCity(String cityName) {
        return centerRepository.findAllByCity(cityName);
    }

    // Trouver tous les centres de vaccination par code postal
    public List<CentreVaccination> findByPostalCode(String postalCode) {
        return centerRepository.findByPostalCode(postalCode);
    }

    // Trouver un centre de vaccination par son nom
    public Optional<CentreVaccination> findByName(String name) {
        return centerRepository.findByName(name);
    }

    // Ajouter un utilisateur à un centre de vaccination
    public Optional<CentreVaccination> addUserToCentreVaccination(Long idCentre, Long idUser) {
        return centerRepository.addUserToCentreVaccination(idCentre, idUser);
    }

    // Supprimer un utilisateur d'un centre de vaccination
    public Optional<CentreVaccination> deleteUserFromCentreVaccination(Long idCentre, Long idUser) {
        return centerRepository.deleteUserFromCentreVaccination(idCentre, idUser);
    }

    // Obtenir tous les utilisateurs d'un centre de vaccination
    public List<CentreVaccination> findAllUsersFromCentreVaccination(Long idCentre) {
        return centerRepository.findAllUsersFromCentreVaccination(idCentre);
    }

    // Obtenir tous les utilisateurs en fonction de leur role et d'un centre de vaccination
    public List<CentreVaccination> findAllUsersByRoleAndCentreVaccination(String role, Long idCentre) {
        return centerRepository.findAllUsersByRoleAndCentreVaccination(role, idCentre);
    }


    

    // Supprimer un centre de vaccination par son id s'il existe
    public void deleteById(Long id) {
        if (centerRepository.findById(id).isPresent()) {
            centerRepository.deleteById(id);
        }
    }
    
    // Ajouter un centre de vaccination
    public CentreVaccination save(CentreVaccination centre) {
        return centerRepository.save(centre);
    }

}
