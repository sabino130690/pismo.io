package com.pismo.io.evaluation.controller.fixture;

import com.pismo.io.evaluation.controller.dto.request.AccountRequest;

public class AccountRequestFixture {
    public static final AccountRequest buildAccountRequest(){
        return AccountRequest.builder()
                .document_number("12345678900")
                .build();
    }
}
