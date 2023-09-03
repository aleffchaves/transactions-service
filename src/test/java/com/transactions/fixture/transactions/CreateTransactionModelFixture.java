package com.transactions.fixture.transactions;

import com.transactions.entities.enums.OperationType;
import com.transactions.interactors.dto.CreateTransactionModel;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.UUID;

@UtilityClass
public class CreateTransactionModelFixture {

    public static CreateTransactionModel createCreateTransactionModelWithOperationType(final OperationType type) {
        return CreateTransactionModel.builder()
                .accountId(UUID.fromString("3927a7c8-6a2a-4f14-91e7-e3582e000456"))
                .amount(BigDecimal.TEN)
                .type(type)
                .build();
    }

    public static CreateTransactionModel createCreateTransactionModel() {
        return CreateTransactionModel.builder()
                .accountId(UUID.fromString("3651c35e-c768-40d0-9216-aff6681aa430"))
                .amount(BigDecimal.TEN)
                .type(OperationType.PAYMENT)
                .build();
    }
}
