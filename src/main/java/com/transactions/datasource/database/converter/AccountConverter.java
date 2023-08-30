package com.transactions.datasource.database.converter;

import com.transactions.datasource.database.models.AccountData;
import com.transactions.entities.Account;

public class AccountConverter {

    private AccountConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Account toAccount(final AccountData account) {
        return Account.builder()
                .id(account.getId())
                .status(account.getStatus())
                .number(account.getNumber())
                .documentNumber(account.getDocumentNumber())
                .balance(account.getBalance())
                .build();
    }

    public static AccountData toAccountData(final Account account) {
        return AccountData.builder()
                .id(account.getId())
                .status(account.getStatus())
                .number(account.getNumber())
                .documentNumber(account.getDocumentNumber())
                .balance(account.getBalance())
                .build();
    }
}
