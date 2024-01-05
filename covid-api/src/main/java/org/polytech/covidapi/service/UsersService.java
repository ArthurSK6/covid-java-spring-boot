package org.polytech.covidapi.service;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.ERole;
import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.repository.UsersRepository;
import org.polytech.covidapi.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersService {

    @Autowired
    private UsersRepository utilisateursRepository;

    @Autowired
    private VaccinationCenterRepository centerRepository;

    // Trouver tous les utilisateurs
    public List<Users> findAll() {
        return utilisateursRepository.findAll();
    }

    // Trouver un utilisateur par son id
    public Optional<Users> findById(Long id) {
        return  utilisateursRepository.findById(id);
    }

    // Trouver tous les utilisateurs par role
    public List<Users> findAllByRole(ERole role) {
        return utilisateursRepository.findAllByRole(role);
    }

    // Trouver tous les utilisateurs par role et centre de vaccination
    public List<Users> findAllUsersByRoleAndVaccinationCenter(ERole role, Long centerId) {
        Optional<VaccinationCenter> center = centerRepository.findById(centerId);
        if (center.isPresent()) {
            return utilisateursRepository.findAllUsersByRoleAndVaccinationCenter(role, center.get());
        } else {
            // Gérer le cas où le centre n'est pas trouvé
            throw new EntityNotFoundException("Centre de vaccination non trouvé avec l'ID fournis.");
        }
    }

    // Supprimer un utilisateur par son id s'il existe
    public boolean deleteById(Long id) {
        if (utilisateursRepository.findById(id).isPresent()) {
            utilisateursRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Supprimer le centre de vaccination d'un utilisateur
    public boolean deleteVaccinationCenterFromUser(Long id) {
        Optional<Users> searchUser = utilisateursRepository.findById(id);
        if (searchUser.isPresent()) {
            Users user = searchUser.get();
            user.setVaccinationCenter(null);
            utilisateursRepository.save(user);
            return true;            
        } 
        return false;
    }

    // Ajouter un utilisateur
    public Users save(Users utilisateur) {
        return utilisateursRepository.save(utilisateur);
    }
}
