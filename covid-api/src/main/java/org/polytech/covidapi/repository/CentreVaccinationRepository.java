package org.polytech.covidapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.polytech.covidapi.domain.CentreVaccination;
@Repository
public interface CentreVaccinationRepository extends JpaRepository<CentreVaccination, Integer> {
    public List<CentreVaccination> findAllByCity(String city);
    public List<CentreVaccination> findAll();
    public Optional<CentreVaccination> findById(Integer id);
    
}