package com.transactions.interactors.usecases;

import com.transactions.entities.Account;
import com.transactions.entities.Transaction;
import com.transactions.entities.enums.OperationType;
import com.transactions.interactors.dto.CreateTransactionModel;
import com.transactions.interactors.dto.CreatedTransactionModel;
import com.transactions.interactors.ports.CreateTransactionUseCasePort;
import com.transactions.providers.AccountsProvider;
import com.transactions.providers.TransactionsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static com.transactions.entities.enums.OperationType.fromValue;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTransactionUseCase implements CreateTransactionUseCasePort {

    private final TransactionsProvider transactionsProvider;
    private final AccountsProvider accountsProvider;

    @Override
    public CreatedTransactionModel execute(final CreateTransactionModel model) {
        log.info("USE CASE - execute - creating a transaction - ACCOUNT ID: {}", model.getAccountId());

        final var account = this.accountsProvider.findAccountById(model.getAccountId().toString());
        final var transaction = this.buildTransaction(model, account);
        this.updateBalanceAccount(account, model);
        this.transactionsProvider.createTransaction(transaction);

        log.info("USE CASE - execute - creating a transaction - ACCOUNT ID: {}", model.getAccountId());

        return this.buildCreatedTransactionModel(transaction);
    }

    private void updateBalanceAccount(final Account account, final CreateTransactionModel transaction) {
        if (OperationType.PAYMENT.equals(transaction.getType())) {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
        } else {
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        }
    }

    private CreatedTransactionModel buildCreatedTransactionModel(final Transaction transaction) {
        return CreatedTransactionModel.builder()
                .id(transaction.getId())
                .build();
    }

    private Transaction buildTransaction(final CreateTransactionModel model, final Account account) {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .type(fromValue(model.getType().getValue()))
                .amount(this.applyNegativeInOperationValue(model))
                .account(account)
                .build();
    }

    private BigDecimal applyNegativeInOperationValue(final CreateTransactionModel model) {
        return model.getType().getValue() != OperationType.PAYMENT.getValue() ?
                model.getAmount().negate() : model.getAmount();
    }
}
