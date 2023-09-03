package com.transactions.transportlayers.dto;

import com.transactions.entities.enums.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Schema(description = "Request schema used to initiate the creation of a new transaction.")
public final class CreateTransactionRequest {

    @Schema(description = "Account identifier.")
    private final UUID accountId;

    @Schema(description = "The type of operation for this transaction.")
    private final OperationType type;

    @Min(1)
    @Schema(description = "The value of the transaction, which must be greater than or equal to 1.")
    private final BigDecimal amount;
}
