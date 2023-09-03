package com.transactions.interactors.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public final class AccountInfoModel {
    private final String id;
    private final String status;
    private final String documentNumber;
    private final String accountNumber;
    private final BigDecimal balance;
}
