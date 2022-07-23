package com.pismo.io.evaluation.usecase;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.gateways.providers.repositories.AccountDatabaseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindAccount {

    @Autowired
    private AccountDatabaseProvider repository;

    public Account execute(final Long id) {

        return repository.findById(id);

    }

}
