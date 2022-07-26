package com.pismo.io.evaluation.controller.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pismo.io.evaluation.controller.converter.TransactionConverter.*;
import static com.pismo.io.evaluation.controller.entities.fixture.TransactionFixture.buildTransaction;
import static com.pismo.io.evaluation.controller.fixture.TransactionRequestFixture.buildTransactionRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests to {@link TransactionConverter}
 */
public class TransactionConverterUnitTest {
    @Test
    @DisplayName("Should grant request data to entity transaction conversion")
    public void testSuccessfulConvertToEntity() {
        final var transactionRequest = buildTransactionRequest();
        final var entityRequest = toTransactionEntity(transactionRequest);

        assertNotNull(entityRequest);
        assertEquals(transactionRequest.getAccount_id(), entityRequest.getAccountId());
        assertEquals(transactionRequest.getAmount(), entityRequest.getAmount());
        assertEquals(transactionRequest.getOperation_type_id(), entityRequest.getOperation().getCode());
    }

    @Test
    @DisplayName("Should grant entity to response transaction conversion")
    public void testSuccessfulConvertToResponse() {
        final var transaction = buildTransaction();
        final var response = toTransactionResponse(transaction);

        assertNotNull(response);
        assertEquals(transaction.getId(), response.getTransaction_id());
    }
}
