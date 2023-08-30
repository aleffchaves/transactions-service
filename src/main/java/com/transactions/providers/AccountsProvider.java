package com.transactions.providers;

import com.transactions.entities.Account;

public interface AccountsProvider {

    boolean checkExistsAccountByDocumentNumber(final String documentNumber);
    void createAccount(final Account documentNumber);
}
