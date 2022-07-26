package com.pismo.io.evaluation.controller.entities.fixture;

import com.pismo.io.evaluation.entities.Transaction;
import com.pismo.io.evaluation.exceptions.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionFixture {
    public static final Transaction buildTransaction() {
        return Transaction.builder()
                .amount(new BigDecimal("12.00"))
                .accountId(1L)
                .operation(OperationType.COMPRA_A_VISTA)
                .eventDatetime(LocalDateTime.now())
                .id(1L)
                .build();
    }

    public static final Transaction buildTransactionCashOut() {
        return Transaction.builder()
                .amount(new BigDecimal("12.00"))
                .accountId(1L)
                .operation(OperationType.PAGAMENTO)
                .eventDatetime(LocalDateTime.now())
                .id(1L)
                .build();
    }
}
