package com.pismo.io.evaluation.controller.validations;


import com.pismo.io.evaluation.controller.validations.validators.DocumentNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Validation for document number format.
 * Accepts CPF and CNPJ values.
 */
@Target({FIELD, CONSTRUCTOR, PARAMETER,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {DocumentNumberValidator.class})
public @interface DocumentNumber {
    String message() default "{documentNumber.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
