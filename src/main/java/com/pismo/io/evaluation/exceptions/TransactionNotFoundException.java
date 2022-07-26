package com.pismo.io.evaluation.exceptions;

import static com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum.TRNS404002;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception when Account not found.
 */
public class TransactionNotFoundException extends AbstractBusinessException {

    public TransactionNotFoundException() {
        super(NOT_FOUND, TRNS404002, true);
    }
}