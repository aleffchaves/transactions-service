package com.transactions.interactors.dto;

import com.transactions.entities.enums.OperationType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public final class CreateTransactionModel {

    private final UUID accountId;
    private final OperationType type;
    private final BigDecimal amount;
}
