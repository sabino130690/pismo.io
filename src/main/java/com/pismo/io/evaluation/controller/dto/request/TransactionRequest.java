package com.pismo.io.evaluation.controller.dto.request;

import com.pismo.io.evaluation.exceptions.enums.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Model representation of a Transaction request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @Schema(description = "Contem o identificador da conta da transação")
    @NotNull(message = "Campo account_id obrigatório")
    private Long account_id;

    @Schema(
            description = "Tipo da operação. Informar o id.",
            type = "enum",
            format = "enum",
            implementation = OperationType.class)
    @NotNull(message = "Campo operation_type_id obrigatório")
    private Long operation_type_id;

    @Schema(description = "Contem o valor da transação.")
    @NotNull(message = "Campo account_id obrigatório")
    private BigDecimal amount;

}