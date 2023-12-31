package org.polytech.covidapi.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // Trouver tous les utilisateurs
    //public List<Users> findAll();   

    // Trouver un utilisateur par son id
    //public Optional<Users> findById(Long id);

    // Trouver tous les utilisateurs par role
    public List<Users> findAllByRole(String role);

    // Ajouter un centre de vaccination à un utilisateur
    //public Users addVaccinationCenterToUser(String name, Long id);

    // Trouver tous les utilisateurs par role et le nom du centre de vaccination
    //public List<Users> findAllByRoleAndVaccinationCenter(String role, String name);

    // Supprimer un utilisateur par son id
    //public Optional<Users> deleteById(Integer id);
} 

