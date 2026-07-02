package com.example.springbootweb.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmplyeeRoleValidation {
    String message() default "Role of the employee can wither be User or admin";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
