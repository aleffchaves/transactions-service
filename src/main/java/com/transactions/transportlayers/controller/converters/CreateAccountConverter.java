package com.transactions.transportlayers.controller.converters;

import com.transactions.entities.enums.StatusAccount;
import com.transactions.interactors.dto.CreatedAccountModel;
import com.transactions.transportlayers.controller.dto.CreatedAccountResponse;

public class CreateAccountConverter {

    private CreateAccountConverter() {
        throw new IllegalStateException("Utility class.");
    }

    public static CreatedAccountResponse toCreatedAccountResponse(final CreatedAccountModel model) {
        return CreatedAccountResponse.builder()
                .status(StatusAccount.ACTIVE.name())
                .accountNumber(model.getAccountNumber())
                .balance(model.getBalance())
                .build();
    }
}