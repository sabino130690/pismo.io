package com.pismo.io.evaluation.controller.dto.request;

import com.pismo.io.evaluation.exceptions.enums.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Model representation of a Transaction request.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotNull(message = "{accountid.notnull}")
    private Long account_id;

    @Schema(
            description = "Id da operação",
            type = "enum",
            format = "enum",
            implementation = OperationType.class)
    @NotNull(message = "{operationtypeid.notnull}")
    private Long operation_type_id;

    @NotNull(message = "{amount.notnull}")
    @DecimalMin(value = "0.01", message = "{amount.min}")
    private BigDecimal amount;

}