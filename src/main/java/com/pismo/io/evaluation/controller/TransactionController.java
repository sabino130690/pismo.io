package com.pismo.io.evaluation.controller;

import com.pismo.io.evaluation.controller.converter.AccountConverter;
import com.pismo.io.evaluation.controller.converter.TransactionConverter;
import com.pismo.io.evaluation.controller.dto.common.ExceptionResponse;
import com.pismo.io.evaluation.controller.dto.request.TransactionRequest;
import com.pismo.io.evaluation.controller.dto.response.TransactionResponse;
import com.pismo.io.evaluation.entities.Transaction;
import com.pismo.io.evaluation.usecase.CreateTransaction;
import com.pismo.io.evaluation.usecase.FindAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/evaluation/transactions")
@Tag(name = "Transaction", description = "Transaction V1")
public class TransactionController {

    private final CreateTransaction createTransaction;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Request a transaction", description = "Makes a transaction request.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Completed Transfer",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransactionResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<TransactionResponse> postTransaction(
            @RequestBody @Valid
            final TransactionRequest transactionRequest){

        log.info("Receiving find transaction request");
        final var createResponse= createTransaction.execute(TransactionConverter.toTransactionEntity(transactionRequest));

        log.info("Returning transaction created");
        return ResponseEntity.ok(TransactionConverter.toTransactionResponse(createResponse));

    }
}
