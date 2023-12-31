package org.polytech.covidapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.repository.UsersRepository;


@Service
public class UsersService {
    @Autowired
    private UsersRepository utilisateursRepository;

    // Trouver tous les utilisateurs
    public List<Users> findAll() {
        return utilisateursRepository.findAll();
    }

    // Trouver un utilisateur par son id
    public Optional<Users> findById(Long id) {
        return  utilisateursRepository.findById(id);
    }

    // Trouver tous les utilisateurs par role
    public List<Users> findAllByRole(String role) {
        return utilisateursRepository.findAllByRole(role);
    }
/* 
    // Trouver tous les utilisateurs par role et centre de vaccination
    public List<Users> findAllByRoleAndVaccinationCenter(String role, String centerName) {
        return utilisateursRepository.findAllByRoleAndVaccinationCenter(role, centerName);
    } */

    // Supprimer un utilisateur par son id s'il existe
    public boolean deleteById(Long id) {
        if (utilisateursRepository.findById(id).isPresent()) {
            utilisateursRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Ajouter un utilisateur
    public Users save(Users utilisateur) {
        return utilisateursRepository.save(utilisateur);
    }
}
