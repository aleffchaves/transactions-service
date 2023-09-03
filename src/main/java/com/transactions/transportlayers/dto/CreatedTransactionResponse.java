package com.transactions.transportlayers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response schema for representing the identify created transaction.")
public final class CreatedTransactionResponse {

    private final String id;
}
