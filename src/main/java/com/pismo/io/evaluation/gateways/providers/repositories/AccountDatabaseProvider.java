package com.pismo.io.evaluation.gateways.providers.repositories;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.gateways.database.AccountRepository;
import com.pismo.io.evaluation.gateways.database.converter.AccountDatabaseConverter;
import com.pismo.io.evaluation.gateways.database.model.AccountData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of all connections with database to return Account information
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountDatabaseProvider implements DatabaseProvider<Account, Long> {

    private final AccountRepository accountRepository;


    @Override
    public Account findById(final Long id) {
        final AccountData accountData = accountRepository.findById(id).orElseThrow(() -> {

            log.info("Account not found");

            return new AccountNotFoundException();
        });
        log.info("Account found");
        return accountData.toEntity();
    }

    /**
     * Method will convert domain database into entity to realize
     *
     * @param document collection identifier
     * @return entity
     */
    public Account findByDocument(final String document) {
        final AccountData accountData = accountRepository.findByDocument(document).orElseThrow(() -> {

            log.info("Account not found by document");

            return new AccountNotFoundException();
        });
        log.info("Account found");
        return accountData.toEntity();
    }


    @Override
    public Account save(final Account account) {

        log.info("Saving account");
        return accountRepository.save(AccountDatabaseConverter.toDatabase(account)).toEntity();
    }

}