package com.pismo.io.evaluation.exceptions;

import static com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum.TRNS404001;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception when Account not found.
 */
public class AccountNotFoundException extends AbstractBusinessException {

    public AccountNotFoundException() {
        super(NOT_FOUND, TRNS404001, true);
    }
}