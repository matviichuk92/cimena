package com.cinema.validation;

import com.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, UserRequestDto> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext validatorContext) {
        return userRequestDto.getPassword() != null
                && userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword());
    }
}
