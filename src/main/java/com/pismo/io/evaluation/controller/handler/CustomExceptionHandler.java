package com.pismo.io.evaluation.controller.handler;

import com.pismo.io.evaluation.controller.dto.common.ExceptionErrorDescriptionResponse;
import com.pismo.io.evaluation.controller.dto.common.ExceptionResponse;
import com.pismo.io.evaluation.exceptions.AbstractBusinessException;
import com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum.TRNS400001;
import static com.pismo.io.evaluation.exceptions.enums.ErrorCodeEnum.TRNS500001;
import static java.util.stream.Collectors.groupingBy;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Handler for all exceptions expected in this application.
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> handleGenericException(
            final Exception e, final HttpServletRequest request) {

        return this.getExceptionResponse(INTERNAL_SERVER_ERROR, request, TRNS500001, null, true);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(
            final ConstraintViolationException e, final HttpServletRequest request) {

        final var fields = this.generateFields(e.getConstraintViolations());
        return this.getExceptionResponse(BAD_REQUEST, request, TRNS400001, fields, true);
    }

    @ExceptionHandler({WebExchangeBindException.class})
    public ResponseEntity<ExceptionResponse> handleWebExchangeBindException(
            final WebExchangeBindException e, final HttpServletRequest request) {

        final var fields = this.generateFields(e.getBindingResult().getFieldErrors());
        return this.getExceptionResponse(BAD_REQUEST, request, TRNS400001, fields, true);
    }

    @ExceptionHandler({ServerWebInputException.class})
    public ResponseEntity<ExceptionResponse> handleServerWebInputException(
            final ServerWebInputException e, final HttpServletRequest request) {

        return this.getExceptionResponse(BAD_REQUEST, request, TRNS400001, null, true);
    }

    @ExceptionHandler({AbstractBusinessException.class})
    public ResponseEntity<ExceptionResponse> handleBusinessException(
            final AbstractBusinessException e, final HttpServletRequest request) {

        return this.getExceptionResponse(e.getStatus(), request, e.getErrorCode(), null, e.isPrintStack());
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ExceptionResponse> handleValidationException(
            final ValidationException e, final HttpServletRequest request) {
        Map<String, List<String>> fieldErros = new HashMap<>();
        fieldErros.put(e.getMessage(), List.of(this.messageSource.getMessage(e.getMessage(), null, e.getMessage(), Locale.getDefault())));
        return this.getExceptionResponse(HttpStatus.BAD_REQUEST, request, TRNS400001, fieldErros, true);
    }

    private ResponseEntity<ExceptionResponse> getExceptionResponse(final HttpStatus status,
                                                                   final HttpServletRequest request,
                                                                   final ErrorCodeEnum code,
                                                                   final Map<String, List<String>> fieldErrors,
                                                                   final boolean isPrintStack) {
        final var response = ExceptionResponse.builder()
                .status(status.value())
                .printStack(isPrintStack)
                .error(this.getError(code, fieldErrors))
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(response);
    }

    private ExceptionErrorDescriptionResponse getError(final ErrorCodeEnum code,
                                                       final Map<String, List<String>> fields) {
        return ExceptionErrorDescriptionResponse.builder()
                .code(code)
                .fields(fields)
                .message(code.getMessage(Locale.getDefault()))
                .build();
    }

    private Map<String, List<String>> generateFields(final List<FieldError> errorValidationList) {

        if (Objects.isNull(errorValidationList) || errorValidationList.isEmpty()) {
            return Collections.emptyMap();
        }

        return errorValidationList.stream()
                .collect(groupingBy(FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList())));

    }

    private Map<String, List<String>> generateFields(final Set<ConstraintViolation<?>> violations) {
        if (Objects.isNull(violations) || violations.isEmpty()) {
            return Collections.emptyMap();
        }

        return violations.stream().collect(
                Collectors.groupingBy(v -> this.getLeafNode(v.getPropertyPath()),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));
    }

    private String getLeafNode(final Path path) {
        final var iterator = path.iterator();
        var node = iterator.next();
        while (iterator.hasNext()) {
            node = iterator.next();
        }
        return node.getName();
    }
}
