package com.transactions.transportlayers.dto;

import com.transactions.entities.enums.OperationType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Tag(name = "operation")
public final class CreateTransactionRequest {

    private final UUID accountId;
    private final OperationType type;
    private final BigDecimal amount;
}
