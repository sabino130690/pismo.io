package com.pismo.io.evaluation.gateways.database.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pismo.io.evaluation.controller.entities.fixture.AccountFixture.buildAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests to {@link AccountDatabaseConverter}
 */
public class AccountDatabaseConverterUnitTest {

    @Test
    @DisplayName("Should grant entity account conversion to database")
    public void testSuccessfulConvertToDatabase() {
        final var account = buildAccount();
        final var accountData = AccountDatabaseConverter.toDatabase(account);

        assertNotNull(accountData);
        assertEquals(account.getDocument(), accountData.getDocument());

    }
}
