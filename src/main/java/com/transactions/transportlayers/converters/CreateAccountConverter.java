package com.transactions.transportlayers.converters;

import com.transactions.entities.enums.StatusAccount;
import com.transactions.interactors.dto.CreatedAccountModel;
import com.transactions.transportlayers.dto.CreatedAccountResponse;

public class CreateAccountConverter {

    private CreateAccountConverter() {
        throw new IllegalStateException("Utility class.");
    }

    public static CreatedAccountResponse toCreatedAccountResponse(final CreatedAccountModel model) {
        return CreatedAccountResponse.builder()
                .id(model.getId())
                .status(StatusAccount.ACTIVE.name())
                .accountNumber(model.getAccountNumber())
                .balance(model.getBalance())
                .build();
    }
}