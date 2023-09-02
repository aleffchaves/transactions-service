package com.transactions.fixture.accounts;

import com.transactions.entities.enums.StatusAccount;
import com.transactions.interactors.dto.CreatedAccountModel;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class CreatedAccountModelFixture {

    public static CreatedAccountModel createCreatedAccountModel() {
        return CreatedAccountModel.builder()
                .id("3927a7c8-6a2a-4f14-91e7-e3582e000456")
                .accountNumber("392778")
                .status(StatusAccount.ACTIVE.name())
                .balance(BigDecimal.TEN)
                .build();
    }
}
