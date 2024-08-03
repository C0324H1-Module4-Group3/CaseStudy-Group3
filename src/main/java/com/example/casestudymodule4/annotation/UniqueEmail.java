package com.example.casestudymodule4.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {
    String message() default "Your registered email is taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
