package com.thietbi247.backend.validator;

import com.thietbi247.backend.validator.impl.DueDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {DueDateValidator.class})
public @interface DueDateConstraint {

    int max();

    String message() default "Please choose a date within {min} days from the borrowing date.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
