package com.pismo.io.evaluation.usecases;

import com.pismo.io.evaluation.exceptions.AccountAlreadyCreatedException;
import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.gateways.providers.repositories.AccountDatabaseProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.pismo.io.evaluation.controller.entities.fixture.AccountFixture.buildAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateAccountUnitTest {

    @InjectMocks
    private CreateAccount createAccount;

    @Mock
    private AccountDatabaseProvider databaseProvider;

    @Test
    @DisplayName("Should fail when create account")
    void testCreateAccountFail() {
        final var account = buildAccount();

        when(databaseProvider.findByDocument(any())).thenReturn(account);
        when(databaseProvider.save(any())).thenReturn(account);

        assertThrows(AccountAlreadyCreatedException.class, () -> createAccount.execute(account));

        verify(databaseProvider).findByDocument(any());
        verify(databaseProvider, never()).save(any());
    }

    @Test
    @DisplayName("Should successfully create account")
    void testCreateAccountSuccessfully() {
        final var account = buildAccount();

        when(databaseProvider.findByDocument(any())).thenThrow(AccountNotFoundException.class);
        when(databaseProvider.save(any())).thenReturn(account);

        assertEquals(account, createAccount.execute(account));

        verify(databaseProvider).findByDocument(any());
        verify(databaseProvider).save(any());
    }

}
