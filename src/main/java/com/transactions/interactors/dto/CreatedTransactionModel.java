package com.transactions.interactors.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CreatedTransactionModel {
    private final String id;
}
