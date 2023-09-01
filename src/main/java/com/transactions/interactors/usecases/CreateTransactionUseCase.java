package com.transactions.interactors.usecases;

import com.transactions.entities.Account;
import com.transactions.entities.Transaction;
import com.transactions.interactors.dto.CreateTransactionModel;
import com.transactions.interactors.dto.CreatedTransactionModel;
import com.transactions.interactors.ports.CreateTransactionUseCasePort;
import com.transactions.providers.AccountsProvider;
import com.transactions.providers.TransactionsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTransactionUseCase implements CreateTransactionUseCasePort {

    private final TransactionsProvider transactionsProvider;
    private final AccountsProvider accountsProvider;

    @Override
    public CreatedTransactionModel execute(final CreateTransactionModel model) {

        log.info("USE CASE - execute - creating a transaction - ACCOUNT ID: " + model.getAccountId());

        final var account = this.accountsProvider.findAccountById(model.getAccountId().toString());
        final var transaction = this.buildTransaction(model, account);
        this.transactionsProvider.createTransaction(transaction);

        log.info("USE CASE - transaction has ben created - TRANSACTION ID: " + transaction.getId());
        return this.buildCreatedTransactionModel(transaction);
    }

    private CreatedTransactionModel buildCreatedTransactionModel(final Transaction transaction) {
        return CreatedTransactionModel.builder()
                .id(transaction.getId())
                .build();
    }

    private Transaction buildTransaction(final CreateTransactionModel model, final Account account) {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .type(model.getType())
                .amount(model.getAmount())
                .account(account)
                .build();
    }
}
