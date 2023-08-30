package com.transactions.transportlayers.controller.converters;

import com.transactions.interactors.dto.AccountInfoModel;
import com.transactions.transportlayers.controller.dto.AccountInfoResponse;

public class AccountInfoConverter {

    private AccountInfoConverter() {
        throw new IllegalStateException("Utility class.");
    }

    public static AccountInfoResponse toAccountInfoResponse(final AccountInfoModel model) {
        return AccountInfoResponse.builder()
                .status(model.getStatus())
                .accountNumber(model.getAccountNumber())
                .documentNumber(model.getDocumentNumber())
                .balance(model.getBalance())
                .build();
    }
}
