package com.pismo.io.evaluation.controller;

import com.pismo.io.evaluation.controller.converter.AccountConverter;
import com.pismo.io.evaluation.controller.dto.common.ExceptionResponse;
import com.pismo.io.evaluation.controller.dto.request.AccountRequest;
import com.pismo.io.evaluation.controller.dto.response.AccountResponse;
import com.pismo.io.evaluation.usecases.CreateAccount;
import com.pismo.io.evaluation.usecases.FindAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/evaluation/accounts")
@RequiredArgsConstructor
@Tag(name = "Account", description = "Account V1")
@Validated
public class AccountController {

    private final FindAccount findAccount;

    private final CreateAccount createAccount;

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

        final var accountResponse = findAccount.execute(accountId);

        return ResponseEntity.ok(AccountConverter.toAccountResponse(accountResponse));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Request a account", description = "Makes a account creation request.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Completed account creation",
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
    public ResponseEntity<AccountResponse> postAccount(
            @RequestBody
            @Valid final AccountRequest accountRequest){


        final var createResponse = createAccount.execute(AccountConverter.toAccountEntity(accountRequest));


        return ResponseEntity.ok(AccountConverter.toAccountResponse(createResponse));

    }
}
