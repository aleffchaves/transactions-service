package com.transactions.fixture.accounts;

import com.transactions.datasource.database.models.AccountData;
import com.transactions.entities.enums.StatusAccount;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class AccountDataFixture {

    public static AccountData createAccountData() {
        return AccountData.builder()
                .id("3927a7c8-6a2a-4f14-91e7-e3582e000456")
                .documentNumber("01186241640")
                .status(StatusAccount.ACTIVE)
                .balance(BigDecimal.TEN)
                .number("8239247")
                .build();
    }

}
