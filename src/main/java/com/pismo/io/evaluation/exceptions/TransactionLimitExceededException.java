package com.pismo.io.evaluation.exceptions;

import static com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum.TRNS412001;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

/**
 * Exception when Account not found.
 */
public class TransactionLimitExceededException extends AbstractBusinessException {

    public TransactionLimitExceededException() {
        super(PRECONDITION_FAILED, TRNS412001, true);
    }
}