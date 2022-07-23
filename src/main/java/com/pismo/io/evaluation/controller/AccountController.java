package com.pismo.io.evaluation.controller;

import com.pismo.io.evaluation.controller.converter.AccountConverter;
import com.pismo.io.evaluation.controller.dto.common.ExceptionResponse;
import com.pismo.io.evaluation.controller.dto.response.AccountResponse;
import com.pismo.io.evaluation.usecase.FindAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/evaluation/accounts")
@Tag(name = "Account", description = "Account V1")
public class AccountController {

    private final FindAccount findAccount;

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find account", description = "Consultation of account by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful find account",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountResponse.class))),
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
    public ResponseEntity<AccountResponse> findAccount(@PathVariable final Long accountId) {
        log.info("Receiving find account request");
        final var accountResponse = findAccount.execute(accountId);

        log.info("Returning find account request");
        return ResponseEntity.ok(AccountConverter.toAccountResponse(accountResponse));
    }
}
