package com.transactions.fixture.transactions;

import com.transactions.entities.Transaction;
import com.transactions.entities.enums.OperationType;
import com.transactions.fixture.accounts.AccountFixture;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class TransactionFixture {

    public static Transaction createTransaction() {
        return Transaction.builder()
                .id("3927a7c8-6a2a-4f14-91e7-e3582e000456")
                .account(AccountFixture.createAccount())
                .amount(BigDecimal.TEN)
                .type(OperationType.PAYMENT)
                .eventDate(LocalDateTime.now())
                .build();
    }


}
