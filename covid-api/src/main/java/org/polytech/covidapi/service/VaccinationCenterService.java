package org.polytech.covidapi.service;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.repository.UsersRepository;
import org.polytech.covidapi.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VaccinationCenterService {
    @Autowired
    private VaccinationCenterRepository centerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    // Création d'un centre CHRU Brabois par défaut au démarrage de l'application
    @PostConstruct
    public void createDefaultVaccinationCenter() {
        if (!this.findById(1L).isPresent()) {
            VaccinationCenter vaccinationCenter = new VaccinationCenter();
            vaccinationCenter.setName("CHRU Brabois");
            vaccinationCenter.setAddress("10 rue Brabois");
            vaccinationCenter.setPostalCode("54000");
            vaccinationCenter.setCity("Brabois");
            this.save(vaccinationCenter);
        }
    }

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

    // Lier un utilisateur à un centre de vaccination
    @Transactional
    public boolean linkUserToVaccinationCenter(Long userId, Long centerId) {
        Optional<Users> userToAdd = usersRepository.findById(userId);
        Optional<VaccinationCenter> centerToAdd = centerRepository.findById(centerId);

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
            return true;
        } else {
            throw new EntityNotFoundException("Utilisateur ou centre de vaccination non trouvé avec les IDs fournis.");
        }
    }

    // Trouver tous les utilisateurs par centre de vaccination
    public List<Users> findAllUsersByVaccinationCenterById(Long centerId) {
        Optional<VaccinationCenter> centerToAdd = centerRepository.findById(centerId);

        if (centerToAdd.isPresent()) {
            VaccinationCenter vaccinationCenter = centerToAdd.get();
            return vaccinationCenter.getUsers();
        } else {
            throw new EntityNotFoundException("Centre de vaccination non trouvé avec l'ID : " + centerId);
        }
    }

    // Supprimer un centre de vaccination par son Id s'il existe
    @Transactional
    public boolean deleteById(Long id) {
        Optional<VaccinationCenter> centerToDelete = centerRepository.findById(id);

        if (centerToDelete.isPresent()) {
            VaccinationCenter vaccinationCenter = centerToDelete.get();

            // Supprimer le centre de vaccination de tous les utilisateurs
            for (Users user : vaccinationCenter.getUsers()) {
                usersService.deleteVaccinationCenterFromUser(user.getId());
            }
            centerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Ajouter un centre de vaccination
    public VaccinationCenter save(VaccinationCenter centre) {
        return centerRepository.save(centre);
    }
}
