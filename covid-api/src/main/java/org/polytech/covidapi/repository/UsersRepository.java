package org.polytech.covidapi.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;

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
    public List<Users> findAllUsersByRoleAndVaccinationCenter(String role, VaccinationCenter center);

    // Supprimer un utilisateur par son id
    //public Optional<Users> deleteById(Integer id);

    // Supprimer le centre de vaccination d'un utilisateur
    //public Optional<Users> deleteVaccinationCenterFromUser(Long id, VaccinationCenter center);
} 

