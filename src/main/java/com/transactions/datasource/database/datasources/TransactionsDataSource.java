package com.transactions.datasource.database.datasources;

import com.transactions.datasource.database.converter.TransactionConverter;
import com.transactions.datasource.database.repository.TransactionRepository;
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
        final var transactionData = TransactionConverter.toTransactionData(transaction);
        this.transactionRepository.save(transactionData);
    }
}
