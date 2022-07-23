package com.pismo.io.evaluation.gateways.database.converter;

import com.pismo.io.evaluation.entities.Transaction;
import com.pismo.io.evaluation.gateways.database.model.TransactionData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionDatabaseConverter {

    public static TransactionData toDatabase(final Transaction transaction) {
        return TransactionData.builder()
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .document(transaction.getDocument())
                .operationId(transaction.getOperationId())
                .build();
    }
}
