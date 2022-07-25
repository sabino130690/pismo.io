package com.pismo.io.evaluation.gateways.providers.repositories;

import com.pismo.io.evaluation.entities.Transaction;
import com.pismo.io.evaluation.exceptions.AccountNotFoundException;
import com.pismo.io.evaluation.gateways.database.TransactionRepository;
import com.pismo.io.evaluation.gateways.database.converter.TransactionDatabaseConverter;
import com.pismo.io.evaluation.gateways.database.model.TransactionData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of all connections with database to return Transaction information
 */
@Slf4j
@Service
@AllArgsConstructor
public class TransactionDatabaseProvider implements DatabaseProvider<Transaction, Long> {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction findById(final Long id) {
        final TransactionData transactionData = transactionRepository.findById(id).orElseThrow(() -> {

            log.info("Transaction not found");

            return new AccountNotFoundException();
        });
        log.info("Transaction found");
        return transactionData.toEntity();
    }

    @Override
    public Transaction save(final Transaction transaction) {
        log.info("Saving Transaction");
        return transactionRepository.save(TransactionDatabaseConverter.toDatabase(transaction)).toEntity();
    }

}