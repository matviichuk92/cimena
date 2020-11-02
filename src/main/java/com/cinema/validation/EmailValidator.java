package com.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    public static final String VALID_EMAIL = "^(.+)@(.+)$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext validatorContext) {
        return email != null && email.matches(VALID_EMAIL);
    }
}
