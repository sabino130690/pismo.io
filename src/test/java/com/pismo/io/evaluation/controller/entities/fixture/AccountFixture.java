package com.pismo.io.evaluation.controller.entities.fixture;

import com.pismo.io.evaluation.entities.Account;

import java.math.BigDecimal;

public class AccountFixture {
    public static final Account buildAccount() {
        return Account.builder()
                .id(1L)
                .document("12345678900")
                .limit(BigDecimal.ZERO)
                .build();
    }

    public static final Account buildAccountWithLimit() {
        return Account.builder()
                .id(1L)
                .document("12345678900")
                .limit(new BigDecimal(120.00))
                .build();
    }
}
