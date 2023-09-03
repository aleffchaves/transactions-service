package com.transactions.fixture.transactions;

import com.transactions.interactors.dto.CreatedTransactionModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreatedTransactionModelFixture {

    public static CreatedTransactionModel createdTransactionModel() {
        return CreatedTransactionModel.builder()
                .id("0b715099-8248-41ab-9f9f-42222589aa4f")
                .build();
    }
}
