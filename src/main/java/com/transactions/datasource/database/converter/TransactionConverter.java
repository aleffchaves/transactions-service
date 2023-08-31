package com.transactions.datasource.database.converter;

import com.transactions.datasource.database.models.AccountData;
import com.transactions.datasource.database.models.TransactionData;
import com.transactions.entities.Account;
import com.transactions.entities.Transaction;

import java.time.LocalDateTime;

public class TransactionConverter {

    private TransactionConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static TransactionData toTransactionData(final Transaction transaction) {
        return TransactionData.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .eventDate(LocalDateTime.now())
                .account(buildAccountData(transaction.getAccount()))
                .build();
    }

    private static AccountData buildAccountData(final Account account) {
        return AccountData.builder()
                .id(account.getId())
                .documentNumber(account.getDocumentNumber())
                .status(account.getStatus())
                .number(account.getNumber())
                .balance(account.getBalance())
                .build();
    }
}
