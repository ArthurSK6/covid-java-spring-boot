package org.polytech.covidapi.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.Utilisateurs;
import org.polytech.covidapi.repository.UtilisateursRepository;


@Service
public class UtilisateursService {
    private UtilisateursRepository utilisateursRepository;

    // Trouver tous les utilisateurs
    public List<Utilisateurs> findAll() {
        return utilisateursRepository.findAll();
    }

    // Trouver un utilisateur par son id
    public Optional<Utilisateurs> findById(Long id) {
        return  utilisateursRepository.findById(id);
    }

    // Trouver un utilisateur par son nom
    public List<Utilisateurs> findByNom(String nom) {
        return utilisateursRepository.findByNom(nom);
    }

    // Trouver un utilisateur par son prénom
    public List<Utilisateurs> findByPrenom(String prenom) {
        return utilisateursRepository.findByPrenom(prenom);
    }

    // Trouver un utilisateur par son rôle
    public List<Utilisateurs> findByRole(String role) {
        return utilisateursRepository.findByRole(role);
    }

    // Trouver un utilisateur par son email
    public Optional<Utilisateurs> findByEmail(String email) {
        return utilisateursRepository.findByEmail(email);
    }

    // Supprimer un utilisateur par son id s'il existe
    public void deleteById(Long id) {
        if (utilisateursRepository.findById(id).isPresent()) {
            utilisateursRepository.deleteById(id);
        }
    }

    // Ajouter un utilisateur
    public Utilisateurs save(Utilisateurs utilisateur) {
        return utilisateursRepository.save(utilisateur);
    }
}
