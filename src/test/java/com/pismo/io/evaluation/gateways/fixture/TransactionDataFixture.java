package com.pismo.io.evaluation.gateways.fixture;

import com.pismo.io.evaluation.gateways.database.model.TransactionData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDataFixture {
    public static final TransactionData buildTransactionData() {
        return TransactionData.builder()
                .id(1L)
                .operationId(1L)
                .accountId(1L)
                .amount(new BigDecimal("12.00"))
                .operationEventDatetime(LocalDateTime.now())
                .build();
    }
}
