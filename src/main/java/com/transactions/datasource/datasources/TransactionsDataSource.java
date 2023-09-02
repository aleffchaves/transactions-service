package com.transactions.datasource.datasources;

import com.transactions.datasource.converter.TransactionConverter;
import com.transactions.datasource.repository.TransactionRepository;
import com.transactions.entities.Transaction;
import com.transactions.providers.TransactionsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionsDataSource implements TransactionsProvider {

    private final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(final Transaction transaction) {
        log.info("DATA SOURCE - createTransaction - TRANSACTION ID: {}", transaction.getId());
        final var transactionData = TransactionConverter.toTransactionData(transaction);
        this.transactionRepository.save(transactionData);
    }
}
