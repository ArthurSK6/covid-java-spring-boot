package org.polytech.covidapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.Rdv;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.repository.RdvRepository;
import org.polytech.covidapi.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RdvService {
    @Autowired
    private RdvRepository rdvRepository;

    @Autowired
    private VaccinationCenterRepository centerRepository;
    
    // Trouver tous les rendez-vous d'une journée en fonction du centre de vaccination
    public List<Rdv> findAllByDateAndVaccinationCenter(Date date, Long idCenter) {
        Optional<VaccinationCenter> vaccinationCenterRequest = centerRepository.findById(idCenter);

        if (vaccinationCenterRequest.isPresent()) {
            VaccinationCenter vaccinationCenter = vaccinationCenterRequest.get();
            return rdvRepository.findAllByDateAndVaccinationCenter(date, vaccinationCenter);
        } else {
            throw new EntityNotFoundException("Centre de vaccination non trouvé avec l'ID fournis.");
        }
    }

    // Trouver le rendez-vous par Id
    public Rdv findById(Long id) {
        return rdvRepository.findById(id).get();
    }

    // Valider un rendez-vous en le mettant vacciné
    @Transactional
    public Rdv validateRdv(Long id) {
        Optional<Rdv> rdv = rdvRepository.findById(id);

        if (rdv.isPresent()) {
            Rdv rdvToValidate = rdv.get();
            rdvToValidate.setVaccinated(true);

            return rdvRepository.save(rdvToValidate);
        } else {
            throw new EntityNotFoundException("Rdv non trouvé avec l'ID fournis.");
        }
    }

    // Ajouter un rendez-vous
    @Transactional
    public Rdv save(Rdv rdv) {
        VaccinationCenter vaccinationCenter = rdv.getVaccinationCenter();

        // Ajouter le rdv au centre de vaccination
        vaccinationCenter.getRdv().add(rdv);
        centerRepository.save(vaccinationCenter);

        return rdvRepository.save(rdv);
    }

    // Supprimer un rendez-vous par son id s'il existe
    public boolean deleteById(Long id) {
        if (rdvRepository.findById(id).isPresent()) {
            rdvRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
