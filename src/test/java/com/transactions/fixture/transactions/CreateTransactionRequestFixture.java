package com.transactions.fixture.transactions;

import com.transactions.entities.enums.OperationType;
import com.transactions.transportlayers.dto.CreateTransactionRequest;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.UUID;

@UtilityClass
public class CreateTransactionRequestFixture {

    public static CreateTransactionRequest createTransactionRequestWithOperationType(final OperationType type) {
        return CreateTransactionRequest.builder()
                .accountId(UUID.fromString("3651c35e-c768-40d0-9216-aff6681aa430"))
                .amount(BigDecimal.TEN)
                .type(type)
                .build();
    }

    public static CreateTransactionRequest createTransactionRequest() {
        return CreateTransactionRequest.builder()
                .accountId(UUID.fromString("3651c35e-c768-40d0-9216-aff6681aa430"))
                .amount(BigDecimal.TEN)
                .type(OperationType.PAYMENT)
                .build();
    }
}
