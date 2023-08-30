package com.transactions.transportlayers.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public final class CreatedAccountResponse {
    private final String status;
    private final String accountNumber;
    private final BigDecimal balance;
}
