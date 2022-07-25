package com.pismo.io.evaluation.gateways.database.converter;

import com.pismo.io.evaluation.entities.Transaction;
import com.pismo.io.evaluation.gateways.database.model.TransactionData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Convert transaction data to account database information.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionDatabaseConverter {

    /**
     * Converts transaction entity object to database obeject
     * @param transaction receives object to be converted
     * @return {@code TransactionData} converted object
     */
    public static TransactionData toDatabase(final Transaction transaction) {
        return TransactionData.builder()
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .operationEventDatetime(transaction.getEventDatetime())
                .operationId(transaction.getOperation().getCode())
                .build();
    }
}
