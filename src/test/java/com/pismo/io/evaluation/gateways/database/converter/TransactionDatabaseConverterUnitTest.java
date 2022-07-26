package com.pismo.io.evaluation.gateways.database.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pismo.io.evaluation.controller.entities.fixture.TransactionFixture.buildTransaction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests to {@link TransactionDatabaseConverter}
 */
public class TransactionDatabaseConverterUnitTest {

    @Test
    @DisplayName("Should grant entity transaction conversion to database")
    public void testSuccessfulConvertToDatabase() {
        final var transaction = buildTransaction();
        final var transactionData = TransactionDatabaseConverter.toDatabase(transaction);

        assertNotNull(transactionData);
        assertEquals(transaction.getAmount(), transactionData.getAmount());
        assertEquals(transaction.getOperation().getCode(), transactionData.getOperationId());
        assertEquals(transaction.getAccountId(), transactionData.getAccountId());
        assertEquals(transaction.getEventDatetime(), transactionData.getOperationEventDatetime());

    }
}
