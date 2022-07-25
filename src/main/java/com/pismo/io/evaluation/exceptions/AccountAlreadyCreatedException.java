package com.pismo.io.evaluation.exceptions;

import static com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum.TRNS422001;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * Exception when Account already exists.
 */
public class AccountAlreadyCreatedException extends AbstractBusinessException {

    public AccountAlreadyCreatedException() {
        super(UNPROCESSABLE_ENTITY, TRNS422001, true);
    }
}