package com.pismo.io.evaluation.controller.converter;

import com.pismo.io.evaluation.controller.dto.request.TransactionRequest;
import com.pismo.io.evaluation.controller.dto.response.AccountResponse;
import com.pismo.io.evaluation.controller.dto.response.TransactionResponse;
import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.entities.Transaction;
import com.pismo.io.evaluation.exceptions.enums.OperationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import static com.pismo.io.evaluation.exceptions.enums.OperationType.*;

/**
 * Converter responsible to convert Transaction information to entity and to controller data.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionConverter {

    /**
     * Builds Transaction entity into transaction response controller object
     * @param transaction contains transaction entity data to be converted
     * @return {@code TransactionResponse} returns transaction controller response entity
     */
    public static TransactionResponse toTransactionResponse(final Transaction transaction){
        return TransactionResponse.builder()
                .transaction_id(transaction.getId())
                .build();
    }

    /**
     * Builds Transaction entity into Account response controller object
     * @param transactionRequest contains transaction entity data to be converted
     * @return {@code Transaction} returns transaction entity
     */
    public static Transaction toTransactionEntity(final TransactionRequest transactionRequest){
        return Transaction.builder()
                .accountId(transactionRequest.getAccount_id())
                .operation(getByCode(transactionRequest.getOperation_type_id()))
                .amount(transactionRequest.getAmount())
                .eventDatetime(LocalDateTime.now())
                .build();
    }
}
