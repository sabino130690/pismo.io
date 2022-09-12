package com.pismo.io.evaluation.usecases;

import com.pismo.io.evaluation.entities.Account;
import com.pismo.io.evaluation.entities.Transaction;
import com.pismo.io.evaluation.exceptions.TransactionLimitExceededException;
import com.pismo.io.evaluation.gateways.providers.repositories.AccountDatabaseProvider;
import com.pismo.io.evaluation.gateways.providers.repositories.TransactionDatabaseProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pismo.io.evaluation.exceptions.enums.OperationType.PAGAMENTO;

/**
 * Use case responsible to Transaction operations.
 */
@Slf4j
@Service
public class CreateTransaction {

    @Autowired
    private TransactionDatabaseProvider transactionDatabaseProvider;

    @Autowired
    private AccountDatabaseProvider accountDatabaseProvider;

    /**
     * Verify if accounts exists and generates a new transaction data.
     * @param transaction {@code Transaction} object identification
     * @return {@code Transaction} returns entity object data
     */
    public Transaction execute(final Transaction transaction) {
        log.info("Verificando existencia da conta.");
        final var account = accountDatabaseProvider.findById(transaction.getAccountId());

        log.info("Salvando transação.");
        return transactionDatabaseProvider.save(buildCashOutAmount(account, transaction));
    }



    private Transaction buildCashOutAmount(final Account account, final Transaction transaction) {
        if (!PAGAMENTO.equals(transaction.getOperation())) {
            log.info("Verificando limite");
            verifyAvalilableLimit(account, transaction);

            log.info("Atualizando limite");
            final var limitToUpdate = account.getLimit();
            account.toBuilder().limit(limitToUpdate.subtract(transaction.getAmount()));
            accountDatabaseProvider.save(account);

            log.info("Fluxo de cash-out");
            final var negateAmount = transaction.getAmount().negate();
            return transaction.toBuilder().amount(negateAmount).build();
        }

        log.info("Atualizando limite");
        final var limitToUpdate = account.getLimit();
        account.toBuilder().limit(limitToUpdate.add(transaction.getAmount()));
        accountDatabaseProvider.save(account);

        log.info("Fluxo de cash-in");
        return transaction;
    }

    private void verifyAvalilableLimit(final Account account, final Transaction transaction){
        if (transaction.getAmount().compareTo(account.getLimit()) > 0){
            throw new TransactionLimitExceededException();
        }
    }

}
