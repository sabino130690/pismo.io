package com.pismo.io.evaluation.controller.fixture;

import com.pismo.io.evaluation.controller.dto.request.TransactionRequest;

import java.math.BigDecimal;

public class TransactionRequestFixture {
    public static final TransactionRequest buildTransactionRequest(){
        return TransactionRequest.builder()
                .amount(new BigDecimal("12.00"))
                .account_id(1L)
                .operation_type_id(1L)
                .build();
    }
}
