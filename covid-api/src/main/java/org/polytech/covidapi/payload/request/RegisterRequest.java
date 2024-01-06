package org.polytech.covidapi.payload.request;

import org.polytech.covidapi.domain.ERole;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.validation.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;
    @NotBlank(message = "Le mot de passe est obligatoire")
    @StrongPassword
    private String password;
    @NotNull
    private ERole role;
    
    private VaccinationCenter vaccinationCenter;
}
