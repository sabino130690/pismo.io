package com.pismo.io.evaluation.usecases;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FindAccountUnitTest {

    @InjectMocks
    private FindAccount findAccount;

    @Mock
    private AccountDatabaseProvider databaseProvider;

    @Test
    @DisplayName("Should fail when account is not found")
    void testCreateAccountFail() {
        final var account = buildAccount();

        when(databaseProvider.findById(any())).thenThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> findAccount.execute(account.getId()));

        verify(databaseProvider).findById(any());
    }

    @Test
    @DisplayName("Should successfully find account")
    void testCreateAccountSuccessfully() {
        final var account = buildAccount();

        when(databaseProvider.findById(any())).thenReturn(account);

        assertEquals(account, findAccount.execute(account.getId()));

        verify(databaseProvider).findById(any());
    }

}
