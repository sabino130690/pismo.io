package com.pismo.io.evaluation.gateways.database.converter;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.gateways.database.model.AccountData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDatabaseConverter {

    public static AccountData toDatabase(final Account account) {
        return AccountData.builder()
                .document(account.getDocument())
                .build();
    }
}
