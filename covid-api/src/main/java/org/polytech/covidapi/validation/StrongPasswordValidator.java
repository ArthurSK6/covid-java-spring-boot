package org.polytech.covidapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String > {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Vérifie si la chaîne contient au moins un chiffre, une lettre minuscule, une lettre majuscule, un caractère spécial et a une longueur d'au moins 8 caractères
        return value.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$");
    }
}
