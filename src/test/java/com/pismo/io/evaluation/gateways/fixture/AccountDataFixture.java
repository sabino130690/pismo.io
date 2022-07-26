package com.pismo.io.evaluation.gateways.fixture;

import com.pismo.io.evaluation.gateways.database.model.AccountData;

public class AccountDataFixture {
    public static final AccountData buildAccountData() {
        return AccountData.builder()
                .id(1L)
                .document("12345678910")
                .build();
    }
}
