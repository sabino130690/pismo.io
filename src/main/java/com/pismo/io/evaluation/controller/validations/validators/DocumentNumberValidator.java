package com.pismo.io.evaluation.controller.validations.validators;

import com.pismo.io.evaluation.controller.validations.DocumentNumber;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validator for CPF or CNPJ values
 */
public class DocumentNumberValidator implements ConstraintValidator<DocumentNumber, String> {

    private static final Pattern CPF_CNPJ_PATTERN = Pattern.compile("(^[0-9]{11}$)|(^[0-9]{14}$)");

    @Override
    public boolean isValid(final String receivedString, final ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isBlank(receivedString) || CPF_CNPJ_PATTERN.matcher(receivedString).matches();
    }

}
