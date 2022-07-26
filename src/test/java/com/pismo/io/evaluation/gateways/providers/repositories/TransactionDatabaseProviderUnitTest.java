package com.pismo.io.evaluation.gateways.providers.repositories;

import com.pismo.io.evaluation.exceptions.TransactionNotFoundException;
import com.pismo.io.evaluation.gateways.database.TransactionRepository;
import com.pismo.io.evaluation.gateways.database.converter.TransactionDatabaseConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.pismo.io.evaluation.controller.entities.fixture.TransactionFixture.buildTransaction;
import static com.pismo.io.evaluation.gateways.fixture.TransactionDataFixture.buildTransactionData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionDatabaseProviderUnitTest {

    @InjectMocks
    private TransactionDatabaseProvider databaseProvider;

    @Mock
    private TransactionRepository transactionRepository;


    @Test
    @DisplayName("Find transaction success")
    void testSuccessFindTransaction() {
        final var transactionData = buildTransactionData();
        when(transactionRepository.findById(transactionData.getId())).thenReturn(Optional.of(transactionData));

        final var response = databaseProvider.findById(transactionData.getId());

        assertNotNull(response);
        assertEquals(transactionData.getAccountId(), response.getAccountId());
        assertEquals(transactionData.getAmount(), response.getAmount());
        assertEquals(transactionData.getOperationId(), response.getOperation().getCode());
        assertEquals(transactionData.getOperationEventDatetime(), response.getEventDatetime());
        assertEquals(transactionData.getId(), response.getId());

    }

    @Test
    @DisplayName("Find transaction fail when not found")
    void testFailFindTransaction() {
        final var transactionData = buildTransactionData();
        when(transactionRepository.findById(transactionData.getId())).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> databaseProvider.findById(transactionData.getId()));

    }

    @Test
    @DisplayName("Save transaction success")
    void testSuccessSaveTransaction() {
        final var transaction = buildTransaction();
        final var transactionData = TransactionDatabaseConverter.toDatabase(transaction);
        when(transactionRepository.save(any())).thenReturn(transactionData);

        final var response = databaseProvider.save(transaction);

        assertNotNull(response);
        assertEquals(transactionData.getAccountId(), response.getAccountId());
        assertEquals(transactionData.getAmount(), response.getAmount());
        assertEquals(transactionData.getOperationId(), response.getOperation().getCode());
        assertEquals(transactionData.getOperationEventDatetime(), response.getEventDatetime());
        assertEquals(transactionData.getId(), response.getId());

    }
}
