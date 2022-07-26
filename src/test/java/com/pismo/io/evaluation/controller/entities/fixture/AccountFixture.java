package com.pismo.io.evaluation.controller.entities.fixture;

import com.pismo.io.evaluation.entities.Account;

public class AccountFixture {
    public static final Account buildAccount() {
        return Account.builder()
                .id(1L)
                .document("12345678900")
                .build();
    }
}
