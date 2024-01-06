package org.polytech.covidapi.validation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
    String message() default "Doit comporter 8 caractères minimum et être une combinaison de lettres majuscules, de lettres minuscules, de chiffres et de caractères spéciaux.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

