package com.transactions.fixture.accounts;

import com.transactions.entities.enums.StatusAccount;
import com.transactions.interactors.dto.AccountInfoModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountInfoModelFixture {

    public static AccountInfoModel createAccountInfoModel() {
        return AccountInfoModel.builder()
                .id("d3fc103c-2fbf-4192-9e5d-b131916d4be0")
                .accountNumber("8234568")
                .status(StatusAccount.ACTIVE.name())
                .documentNumber("13785807295")
                .build();
    }
}
