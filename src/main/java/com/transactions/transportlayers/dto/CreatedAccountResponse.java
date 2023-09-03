package com.transactions.transportlayers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "Response schema representing the details of the created account.")
public final class CreatedAccountResponse {

    @Schema(description = "The unique identifier of the created account.")
    private final String id;

    @Schema(description = "The status of the created account.")
    private final String status;

    @Schema(description = "The account number associated with the created account.")
    private final String accountNumber;

    @Schema(description = "The current balance of the created account.")
    private final BigDecimal balance;
}
