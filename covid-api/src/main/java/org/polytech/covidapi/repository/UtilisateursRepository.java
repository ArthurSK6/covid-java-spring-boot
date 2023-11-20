package org.polytech.covidapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.Utilisateurs;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long> {

    public List<Utilisateurs> findAll();   // Trouver tous les utilisateurs
    public Optional<Utilisateurs> findById(Long id);   // Trouver un utilisateur par son id
    public List<Utilisateurs> findByNom(String nom);   // Trouver un utilisateur par son nom
    public List<Utilisateurs> findByPrenom(String prenom);   // Trouver un utilisateur par son prénom
    public List<Utilisateurs> findByRole(String role);   // Trouver un utilisateur par son rôle
    public Optional<Utilisateurs> findByEmail(String email);   // Trouver un utilisateur par son email

    public void deleteById(Integer id);   // Supprimer un utilisateur par son id
} 

