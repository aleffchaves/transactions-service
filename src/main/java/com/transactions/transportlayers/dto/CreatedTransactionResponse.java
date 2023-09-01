package com.transactions.transportlayers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CreatedTransactionResponse {

    private final String id;
}
