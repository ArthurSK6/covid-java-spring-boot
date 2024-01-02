package org.polytech.covidapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.repository.UsersRepository;
import org.polytech.covidapi.repository.VaccinationCenterRepository;

@Service
public class VaccinationCenterService {
    @Autowired
    private VaccinationCenterRepository centerRepository;

    @Autowired
    private UsersRepository usersRepository;

    // Trouver tous les centres de vaccination
    public List<VaccinationCenter> findAll() {
        return centerRepository.findAll();
    }

    // Trouver un centre de vaccination par son id
    public Optional<VaccinationCenter> findById(Long id) {
        return centerRepository.findById(id);
    }

    // Trouver tous les centres de vaccination par ville
    public List<VaccinationCenter> findAllByCity(String cityName) {
        return centerRepository.findAllByCity(cityName);
    }

    // Trouver tous les centres de vaccination par code postal
    public List<VaccinationCenter> findAllByPostalCode(String postalCode) {
        return centerRepository.findAllByPostalCode(postalCode);
    }

    // Trouver un centre de vaccination par son nom
    public Optional<VaccinationCenter> findByName(String name) {
        return centerRepository.findByName(name);
    }

/*     // Ajouter un utilisateur à un centre de vaccination
    public Optional<VaccinationCenter> addUserToVaccinationCenter(Long id, String name) {
        if (centerRepository.findByName(name).isPresent()) {

            // Récupérer l'id du centre de vaccination
            id = centerRepository.findByName(name).get().getId();

            return centerRepository.addUserToVaccinationCenter(id, name);
        }
        return null;
    } */

    // Lier un utilisateur à un centre de vaccination
    @Transactional
    public void linkUserToVaccinationCenter(Long userId, String centerName) {
        Optional<Users> userToAdd = usersRepository.findById(userId);
        Optional<VaccinationCenter> centerToAdd = centerRepository.findByName(centerName);

        if (userToAdd.isPresent() && centerToAdd.isPresent()) {
            // Récupérer l'utilisateur et le centre de vaccination
            Users user = userToAdd.get();
            VaccinationCenter vaccinationCenter = centerToAdd.get();

            // Associer l'utilisateur au centre de vaccination
            user.setVaccinationCenter(vaccinationCenter);
            usersRepository.save(user);

            // Ajouter l'utilisateur au centre de vaccination
            vaccinationCenter.getUsers().add(user);
            centerRepository.save(vaccinationCenter);
        } else {
            throw new EntityNotFoundException("Utilisateur ou centre de vaccination non trouvé avec les IDs fournis.");
        }
    }

    // Trouver tous les utilisateurs par centre de vaccination
    public List<Users> findAllUsersByVaccinationCenter(String centerName) {
        Optional<VaccinationCenter> centerToAdd = centerRepository.findByName(centerName);

        if (centerToAdd.isPresent()) {
            VaccinationCenter vaccinationCenter = centerToAdd.get();
            return vaccinationCenter.getUsers();
        } else {
            throw new EntityNotFoundException("Centre de vaccination non trouvé avec le nom : " + centerName);
        }
    }

    // Supprimer un centre de vaccination par son Nom
    @Transactional
    public Long deleteByName(String name) {
        if (centerRepository.findByName(name).isPresent()) {
            return centerRepository.deleteByName(name);
        }
        return null;
    }
    
    // Ajouter un centre de vaccination
    public VaccinationCenter save(VaccinationCenter centre) {
        return centerRepository.save(centre);
    }

}
