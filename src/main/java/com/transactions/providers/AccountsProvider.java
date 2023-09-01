package com.transactions.providers;

import com.transactions.entities.Account;

public interface AccountsProvider {

    boolean checkExistsAccountByDocumentNumber(final String documentNumber);

    Account findAccountById(final String accountId);

    void createAccount(final Account documentNumber);

    Account getAccountInfo(final String accountId);
}
