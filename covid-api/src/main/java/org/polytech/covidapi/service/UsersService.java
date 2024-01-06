package org.polytech.covidapi.service;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.ERole;
import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.payload.request.RegisterRequest;
import org.polytech.covidapi.repository.RefreshTokenRepository;
import org.polytech.covidapi.repository.UsersRepository;
import org.polytech.covidapi.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersService {

    @Autowired
    private UsersRepository utilisateursRepository;

    @Autowired
    private VaccinationCenterRepository centerRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AuthenticationService authenticationService;

    // Création d'un utilisateur super-admin, adin et docteur par défaut au démarrage de l'application
    @PostConstruct
    public void createDefaultSuperAdmin() {
        if (!this.findById(1L).isPresent()) {
            RegisterRequest superAdmin = new RegisterRequest();
            superAdmin.setNom("Super");
            superAdmin.setPrenom("Admin");
            superAdmin.setEmail("super@admin.com");
            superAdmin.setPassword("super@admin.com1");
            superAdmin.setRole(ERole.SUPERADMIN);
            authenticationService.register(superAdmin);
        }
        if (!this.findById(2L).isPresent()) {
            RegisterRequest superAdmin = new RegisterRequest();
            superAdmin.setNom("Admin");
            superAdmin.setPrenom("Admin");
            superAdmin.setEmail("admin@admin.com");
            superAdmin.setPassword("admin@admin.com1");
            superAdmin.setRole(ERole.ADMIN);
            authenticationService.register(superAdmin);
        }
        if (!this.findById(3L).isPresent()) {
            RegisterRequest superAdmin = new RegisterRequest();
            superAdmin.setNom("Docteur");
            superAdmin.setPrenom("Docteur");
            superAdmin.setEmail("docteur@docteur.com");
            superAdmin.setPassword("docteur@docteur.com1");
            superAdmin.setRole(ERole.DOCTOR);
            authenticationService.register(superAdmin);
        }
    }

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
            refreshTokenRepository.deleteByUserId(id);
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
