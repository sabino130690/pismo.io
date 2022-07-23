package com.pismo.io.evaluation.exceptions;

import com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Abstract exception with enough data to format all error responses in application.
 */
@Getter
public abstract class AbstractBusinessException extends RuntimeException {

    private static final long serialVersionUID = -6976766721593829348L;
    private final ErrorCodeEnum errorCode;
    private final HttpStatus status;
    private final boolean printStack;

    protected AbstractBusinessException(final HttpStatus status, final ErrorCodeEnum errorCode,
                                        final boolean printStack) {
        this.errorCode = errorCode;
        this.status = status;
        this.printStack = printStack;
    }

    protected AbstractBusinessException(final HttpStatus status, final ErrorCodeEnum errorCode, final Throwable cause,
                                        final boolean printStack) {
        super(cause);
        this.errorCode = errorCode;
        this.status = status;
        this.printStack = printStack;
    }

}
