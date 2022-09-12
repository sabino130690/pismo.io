package com.pismo.io.evaluation.usecases;

import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.gateways.providers.repositories.AccountDatabaseProvider;
import com.pismo.io.evaluation.gateways.providers.repositories.TransactionDatabaseProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.pismo.io.evaluation.controller.entities.fixture.AccountFixture.buildAccount;
import static com.pismo.io.evaluation.controller.entities.fixture.AccountFixture.buildAccountWithLimit;
import static com.pismo.io.evaluation.controller.entities.fixture.TransactionFixture.buildTransaction;
import static com.pismo.io.evaluation.controller.entities.fixture.TransactionFixture.buildTransactionCashIn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateTransactionUnitTest {

    @InjectMocks
    private CreateTransaction createTransaction;

    @Mock
    private AccountDatabaseProvider accountDatabaseProvider;

    @Mock
    private TransactionDatabaseProvider transactionDatabaseProvider;

    @Test
    @DisplayName("Should fail create transaction when account is not found")
    void testCreateTransactionFail() {
        final var transaction = buildTransaction();

        when(accountDatabaseProvider.findById(any())).thenThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> createTransaction.execute(transaction));

        verify(accountDatabaseProvider).findById(any());
        verify(transactionDatabaseProvider, never()).save(any());
    }

    @Test
    @DisplayName("Should successfully create transaction")
    void testCreateTransactionSuccessfully() {
        final var account = buildAccountWithLimit();
        final var transaction = buildTransaction();

        when(accountDatabaseProvider.findById(any())).thenReturn(account);
        when(transactionDatabaseProvider.save(any())).thenReturn(transaction);

        assertEquals(transaction, createTransaction.execute(transaction));

        verify(accountDatabaseProvider).findById(any());
        verify(transactionDatabaseProvider).save(any());
    }

    @Test
    @DisplayName("Should successfully create transaction with CashOut OperationType")
    void testCreateTransactionSuccessfullyWithCashOutAmount() {
        final var account = buildAccount();
        final var transaction = buildTransactionCashIn();

        when(accountDatabaseProvider.findById(any())).thenReturn(account);
        when(transactionDatabaseProvider.save(any())).thenReturn(transaction);

        assertEquals(transaction, createTransaction.execute(transaction));

        verify(accountDatabaseProvider).findById(any());
        verify(transactionDatabaseProvider).save(any());
    }

}
