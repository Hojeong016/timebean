package com.hj.timebean.validation;

import com.hj.timebean.dto.SignUpDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpDTO> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(SignUpDTO value, ConstraintValidatorContext context) {
        System.out.println(value.toString());
        return value.getPassword().equals(value.getPasswordConfirm());
    }
}
