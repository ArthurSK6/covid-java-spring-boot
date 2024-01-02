package org.polytech.covidapi.web;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.ERole;
import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api")
public class UsersControleur {
    @Autowired
    private UsersService utilisateursService;

    // Définition variable URL superadmin
    private static final String URL_SUPERADMIN = "/superadmin/user";

    // Définition variable URL admin
    private static final String URL_ADMIN = "/admin/user";

    // Obtenir tous les Users
    @GetMapping(path = URL_SUPERADMIN + "/all")
    public List<Users> getAllUsers() {
        return utilisateursService.findAll();
    }

    // Obtenir un utilisateur par son id
    @GetMapping(path = URL_ADMIN + "/id/{id}")
    public Optional<Users> getUserbyID(
        @PathVariable("id") Long id) {
        return utilisateursService.findById(id);
    }

    // Obtenir tous les utilisateurs par rôle
    @GetMapping(path = URL_ADMIN + "/role/{role}")
    public List<Users> getUsersByRole(
        @PathVariable("role") ERole role) {
        return utilisateursService.findAllByRole(role);
    }

    // Obtenir tous les utilisateurs par rôle et centre de vaccination
    @GetMapping(path = URL_ADMIN + "/role/{role}/{centerId}")
    public List<Users> getAllUsersByRoleAndCenter(
        @PathVariable("role") ERole role, 
        @PathVariable("centerId") Long centerId) {
        return utilisateursService.findAllUsersByRoleAndVaccinationCenter(role, centerId);
    }

    // Supprimer un utilisateur par son id s'il existe
    @DeleteMapping(path = URL_SUPERADMIN + "/delete/{id}")
    public boolean deleteUtilisateurById(
        @PathVariable("id") Long id) {
        return utilisateursService.deleteById(id);
    }

    // Supprimer le centre de vaccination d'un utilisateur
    @PutMapping(path = URL_ADMIN + "/deletecenter/{id}")
    public boolean deleteVaccinationCenterFromUser(
        @PathVariable("id") Long id) {
        return utilisateursService.deleteVaccinationCenterFromUser(id);
    }

    // Ajouter un utilisateur docteur
    @PostMapping(path = URL_ADMIN + "/add")
    public Users addUtilisateurDocteur(@RequestBody Users utilisateur) {
        utilisateur.setRole(ERole.ROLE_DOCTOR);
        return utilisateursService.save(utilisateur);
    }

    // Ajouter un utilisateur tout type
    @PostMapping(path = URL_SUPERADMIN + "/add")
    public Users addUtilisateur(@RequestBody Users utilisateur) {
        return utilisateursService.save(utilisateur);
    }

    // Modifier un utilisateur
    @PutMapping(path = URL_SUPERADMIN + "/update")
    public Users updateUtilisateur(@RequestBody Users utilisateur) {
        return utilisateursService.save(utilisateur);
    }
}
