package com.cinema.validation;

import com.cinema.model.dto.UserRequestDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext validatorContext) {
        return userRequestDto.getPassword() != null
                && Objects.equals(userRequestDto.getPassword(), userRequestDto.getRepeatPassword());
    }
}
