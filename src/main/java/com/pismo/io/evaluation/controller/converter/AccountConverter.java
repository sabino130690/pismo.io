package com.pismo.io.evaluation.controller.converter;

import com.pismo.io.evaluation.controller.dto.response.AccountResponse;
import com.pismo.io.evaluation.entities.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountConverter {

    public static AccountResponse toAccountResponse(final Account account){
        return AccountResponse.builder()
                .account_id(account.getId())
                .document_number(account.getDocument())
                .build();
    }
}
