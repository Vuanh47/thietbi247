package com.thietbi247.backend.validator.impl;

import com.thietbi247.backend.validator.DueDateConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DueDateValidator implements ConstraintValidator<DueDateConstraint, LocalDateTime> {
    private int max;
    @Override
    public void initialize(DueDateConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        max = constraintAnnotation.max();

    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null || max < 1) {
            return true;
        }

        long daysBetween = ChronoUnit.DAYS.between(LocalDateTime.now(), value);
        return daysBetween >= 0 && daysBetween <= max;
    }

}
