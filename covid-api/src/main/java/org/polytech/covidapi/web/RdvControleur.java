package org.polytech.covidapi.web;

import java.util.Date;
import java.util.List;

import org.polytech.covidapi.domain.Rdv;
import org.polytech.covidapi.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/rdv")
public class RdvControleur {
    @Autowired
    private RdvService rdvService;
    
    // Obtenir tous les rendez-vous d'une journée
    @GetMapping(path = "/date")
    public List<Rdv> getAllRdvByDate(@RequestBody Date date) {
        return rdvService.findAllByDate(date);
    }

    // Obtenir un rendez-vous par son id
    @GetMapping(path = "/id/{id}")
    public Rdv getRdvById(
        @PathVariable("id") Long id) {
        return rdvService.findById(id);
    }

    // Valider un rendez-vous en le mettant vacciné
    @PostMapping(path = "/validate/{id}")
    public Rdv validateRdv(
        @PathVariable("id") Long id) {
        return rdvService.validateRdv(id);
    }

    // Supprimer un rendez-vous par son id s'il existe
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteRdvById(
        @PathVariable("id") Long id) {
        return rdvService.deleteById(id);
    }

    // Ajouter un rendez-vous
    @PostMapping(path = "/add")
    public Rdv addRdv(@RequestBody Rdv rdv) {
        return rdvService.save(rdv);
    }   
    
}

