package org.polytech.covidapi.repository;

import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.ERole;
import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // Trouver tous les utilisateurs par role
    public List<Users> findAllByRole(ERole role);

    // Trouver tous les utilisateurs par role et le nom du centre de vaccination
    public List<Users> findAllUsersByRoleAndVaccinationCenter(ERole role, VaccinationCenter center);

    // Trouver un utilisateur par son email
    public Optional<Users> findByEmail(String email);
} 