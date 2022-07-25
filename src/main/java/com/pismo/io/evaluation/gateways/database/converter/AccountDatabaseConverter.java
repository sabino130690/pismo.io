package com.pismo.io.evaluation.gateways.database.converter;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.gateways.database.model.AccountData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Convert account data to account database information.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDatabaseConverter {

    /**
     * Converts account entity object to database obeject
     * @param account receives object to be converted
     * @return {@code AccountData} converted object
     */
    public static AccountData toDatabase(final Account account) {
        return AccountData.builder()
                .document(account.getDocument())
                .build();
    }
}
