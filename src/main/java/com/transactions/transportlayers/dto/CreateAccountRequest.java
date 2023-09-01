package com.transactions.transportlayers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
public final class CreateAccountRequest {

    @CPF
    @NotBlank
    private final String documentNumber;
}
