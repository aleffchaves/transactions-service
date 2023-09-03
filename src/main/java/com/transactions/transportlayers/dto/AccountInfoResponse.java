package com.transactions.transportlayers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "Response schema representing account information.")
public final class AccountInfoResponse {

    @Schema(description = "The unique identifier of the account.")
    private final String id;

    @Schema(description = "The status of the account.")
    private final String status;

    @Schema(description = "The document number associated with the account.")
    private final String documentNumber;

    @Schema(description = "The account number.")
    private final String accountNumber;

    @Schema(description = "The current balance of the account.")
    private final BigDecimal balance;
}
