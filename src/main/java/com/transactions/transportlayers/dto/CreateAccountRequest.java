package com.transactions.transportlayers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@Schema(description = "Request schema used to initiate the creation of a new account.")
public final class CreateAccountRequest {

    @CPF
    @NotBlank
    @Schema(description = "Customer document number.")
    private final String documentNumber;
}
