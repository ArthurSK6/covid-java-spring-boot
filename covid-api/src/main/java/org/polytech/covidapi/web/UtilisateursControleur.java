package org.polytech.covidapi.web;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.Utilisateurs;
import org.polytech.covidapi.service.UtilisateursService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/utilisateurs")
public class UtilisateursControleur {
    private UtilisateursService utilisateursService;

    // Obtenir tous les utilisateurs
    @GetMapping(path = "/")
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateursService.findAll();
    }

    // Obtenir un utilisateur par son id
    @GetMapping(path = "/{id}")
    public Optional<Utilisateurs> getUtilisateurbyID(
        @PathVariable("id") Long id) {
        return utilisateursService.findById(id);
    }

    // Obtenir un utilisateur par son nom
    @GetMapping(path = "/")
    public List<Utilisateurs> getUtilisateurByNom(
        @PathVariable("nom") String nom) {
        return utilisateursService.findByNom(nom);
    }

    // Obtenir un utilisateur par son prénom
    @GetMapping(path = "/")
    public List<Utilisateurs> getUtilisateurByPrenom(
        @PathVariable("prenom") String prenom) {
        return utilisateursService.findByPrenom(prenom);
    }

    // Obtenir un utilisateur par son rôle
    @GetMapping(path = "/")
    public List<Utilisateurs> getUtilisateurByRole(
        @PathVariable("role") String role) {
        return utilisateursService.findByRole(role);
    }

    // Obtenir un utilisateur par son email
    @GetMapping(path = "/")
    public Optional<Utilisateurs> getUtilisateurByEmail(
        @PathVariable("email") String email) {
        return utilisateursService.findByEmail(email);
    }

    // Supprimer un utilisateur par son id s'il existe
    @GetMapping(path = "/")
    public void deleteUtilisateurById(
        @PathVariable("id") Long id) {
        utilisateursService.deleteById(id);
    }

    // Ajouter un utilisateur
    @GetMapping(path = "/")
    public Utilisateurs addUtilisateur(
        @PathVariable("utilisateur") Utilisateurs utilisateur) {
        return utilisateursService.save(utilisateur);
    }
}
