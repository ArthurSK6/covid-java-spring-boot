package org.polytech.covidapi.web;

import java.util.Date;
import java.util.List;

import org.polytech.covidapi.domain.Rdv;
import org.polytech.covidapi.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class RdvControleur {
    @Autowired
    private RdvService rdvService;

    // Définition variable URL public
    private static final String URL_PUBLIC = "/public/rdv";

    // Définition variable URL docteur
    private static final String URL_DOCTOR = "/doctor/rdv";

    
    // Obtenir tous les rendez-vous d'une journée (privé)
    @GetMapping(path = URL_DOCTOR + "/date")
    public List<Rdv> getAllRdvByDateAndVaccinationCenter(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
        @RequestParam("idCenter") Long idCenter) {
        return rdvService.findAllByDateAndVaccinationCenter(date, idCenter);
    }

    // Obtenir un rendez-vous par son id (privé)
    @GetMapping(path = URL_DOCTOR + "/id/{id}")
    public Rdv getRdvById(
        @PathVariable("id") Long id) {
        return rdvService.findById(id);
    }

    // Valider un rendez-vous en le mettant vacciné (privé)
    @PutMapping(path = URL_DOCTOR + "/validate/{id}")
    public Rdv validateRdv(
        @PathVariable("id") Long id) {
        return rdvService.validateRdv(id);
    }

    // Supprimer un rendez-vous par son id s'il existe (privé)
    @DeleteMapping(path = URL_DOCTOR + "/delete/{id}")
    public boolean deleteRdvById(
        @PathVariable("id") Long id) {
        return rdvService.deleteById(id);
    }

    // Ajouter un rendez-vous (public)
    @PostMapping(path = URL_PUBLIC + "/add")
    public Rdv addRdv(@RequestBody Rdv rdv ) {
        return rdvService.save(rdv);
    }   
    
}

