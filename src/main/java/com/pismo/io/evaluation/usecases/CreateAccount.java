package com.pismo.io.evaluation.usecases;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.exceptions.AccountAlreadyCreatedException;
import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.gateways.providers.repositories.AccountDatabaseProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Use case responsible to Account creation operations.
 */
@Slf4j
@Service
public class CreateAccount {
    @Autowired
    private AccountDatabaseProvider repository;

    /**
     * Verify if accounts exists and generates a new account data. Throws an error if account exists.
     * @param account {@code Account} object
     * @return {@code Account} returns entity object data
     */
    public Account execute(final Account account) {
        try {
            log.info("Verificando existencia da conta.");
            repository.findByDocument(account.getDocument());
        }catch (AccountNotFoundException anfe){
            log.info("Conta não encontrada, criando conta");

            return repository.save(initializeLimit(account));
        }
        log.error("Conta ja existe");
        throw new AccountAlreadyCreatedException();

    }

    private Account initializeLimit(final Account account){
        log.info("Adicionando limite zero a conta a ser criada.");
        return account.toBuilder().limit(BigDecimal.ZERO).build();
    }
}
