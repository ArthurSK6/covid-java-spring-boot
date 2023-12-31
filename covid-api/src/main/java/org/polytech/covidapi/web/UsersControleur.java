package org.polytech.covidapi.web;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/user")
public class UsersControleur {
    @Autowired
    private UsersService utilisateursService;

    // Obtenir tous les Users
    @GetMapping(path = "/all")
    public List<Users> getAllUsers() {
        return utilisateursService.findAll();
    }

    // Obtenir un utilisateur par son id
    @GetMapping(path = "/id/{id}")
    public Optional<Users> getUserbyID(
        @PathVariable("id") Long id) {
        return utilisateursService.findById(id);
    }

    // Obtenir tous les utilisateurs par rôle
    @GetMapping(path = "/role/{role}")
    public List<Users> getUsersByRole(
        @PathVariable("role") String role) {
        return utilisateursService.findAllByRole(role);
    }

    // Supprimer un utilisateur par son id s'il existe
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteUtilisateurById(
        @PathVariable("id") Long id) {
        return utilisateursService.deleteById(id);
    }


    // Ajouter un utilisateur
    @PostMapping(path = "/add")
    public Users addUtilisateur(@RequestBody Users utilisateur) {
        return utilisateursService.save(utilisateur);
    }
}
