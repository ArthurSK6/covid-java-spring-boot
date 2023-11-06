package org.polytech.covidapi.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.polytech.covidapi.domain.CentreVaccination;
import org.polytech.covidapi.service.CentreVaccinationService;

@RestController
public class CentreVaccinationController {
    @Autowired
    private CentreVaccinationService centerService;
    //Chercher dans queryparam pourcity
   // /api/centers?city=Nancy
   // /api/center/12
   // /api/centers?city=Nancy&open=true


    @GetMapping(path = "/api/center")
    public List<CentreVaccination> get(
         @RequestParam("city") String city) {
        return centerService.findAllByCity(city);
    }

    
    @GetMapping(path = "/api/center/{id}")
    public Optional<CentreVaccination> get(
        @PathVariable("id") Integer id) {
        return centerService.findById(id);
    }
    

    @GetMapping(path = "/api/center/")
    public List<CentreVaccination> getAllCenter() {
        return centerService.findAll();
    }
}