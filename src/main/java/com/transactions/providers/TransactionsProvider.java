package com.transactions.providers;

import com.transactions.entities.Transaction;

public interface TransactionsProvider {

    void createTransaction(final Transaction transaction);
}
