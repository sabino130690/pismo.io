package com.pismo.io.evaluation.usecases;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.gateways.providers.repositories.AccountDatabaseProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Use case responsible to Account lockup operations.
 */
@Slf4j
@Service
public class FindAccount {

    @Autowired
    private AccountDatabaseProvider repository;


    /**
     * Retrieves account information data
     * @param id {@code Long} Account identification
     * @return {@code Account} returns entity object data
     */
    public Account execute(final Long id) {
        log.info("Procurando conta.");
        return repository.findById(id);

    }

}
