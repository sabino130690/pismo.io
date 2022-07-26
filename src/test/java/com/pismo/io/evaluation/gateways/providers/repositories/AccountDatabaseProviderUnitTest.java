package com.pismo.io.evaluation.gateways.providers.repositories;

import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.gateways.database.AccountRepository;
import com.pismo.io.evaluation.gateways.database.converter.AccountDatabaseConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.pismo.io.evaluation.controller.entities.fixture.AccountFixture.buildAccount;
import static com.pismo.io.evaluation.gateways.fixture.AccountDataFixture.buildAccountData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountDatabaseProviderUnitTest {

    @InjectMocks
    private AccountDatabaseProvider databaseProvider;

    @Mock
    private AccountRepository accountRepository;


    @Test
    @DisplayName("Find account success")
    void testSuccessFindAccount() {
        final var accountData = buildAccountData();
        when(accountRepository.findById(accountData.getId())).thenReturn(Optional.of(accountData));

        final var response = databaseProvider.findById(accountData.getId());

        assertNotNull(response);
        assertEquals(accountData.getDocument(), response.getDocument());
        assertEquals(accountData.getId(), response.getId());

    }

    @Test
    @DisplayName("Find account fail when not found")
    void testFailFindAccount() {
        final var accountData = buildAccountData();
        when(accountRepository.findById(accountData.getId())).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () ->  databaseProvider.findById(accountData.getId()));

    }

    @Test
    @DisplayName("Find account by document success")
    void testSuccessFindAccountByDocument() {
        final var accountData = buildAccountData();
        when(accountRepository.findByDocument(accountData.getDocument())).thenReturn(Optional.of(accountData));

        final var response = databaseProvider.findByDocument(accountData.getDocument());

        assertNotNull(response);
        assertEquals(accountData.getDocument(), response.getDocument());
        assertEquals(accountData.getId(), response.getId());

    }

    @Test
    @DisplayName("Find account by document fail when not found")
    void testFailFindAccountByDocument() {
        final var accountData = buildAccountData();
        when(accountRepository.findByDocument(accountData.getDocument())).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () ->  databaseProvider.findByDocument(accountData.getDocument()));

    }

    @Test
    @DisplayName("Save account success")
    void testSuccessSaveAccount() {
        final var account = buildAccount();
        final var accountData = AccountDatabaseConverter.toDatabase(account);
        when(accountRepository.save(any())).thenReturn(accountData);

        final var response = databaseProvider.save(account);

        assertNotNull(response);
        assertEquals(accountData.getDocument(), response.getDocument());
        assertEquals(accountData.getId(), response.getId());

    }
}
