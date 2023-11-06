package org.polytech.covidapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.polytech.covidapi.domain.CentreVaccination;
import org.polytech.covidapi.repository.CentreVaccinationRepository;

@Service
public class CentreVaccinationService {
        @Autowired
        private CentreVaccinationRepository centerRepository;

        public List<CentreVaccination> findAllByCity(String cityName) {
            return centerRepository.findAllByCity(cityName);
        }

        public List<CentreVaccination> findAll() {
            return centerRepository.findAll();
        }
        
        public Optional<CentreVaccination> findById(Integer id) {
            return  centerRepository.findById(id);
        }

}