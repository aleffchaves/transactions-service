package com.transactions.transportlayers.converters;

import com.transactions.interactors.dto.CreateTransactionModel;
import com.transactions.interactors.dto.CreatedTransactionModel;
import com.transactions.transportlayers.dto.CreateTransactionRequest;
import com.transactions.transportlayers.dto.CreatedTransactionResponse;

public class TransactionConverter {

    private TransactionConverter() {
        throw new IllegalStateException("Utility class.");
    }

    public static CreateTransactionModel toCreateTransactionModel(final CreateTransactionRequest request) {
        return CreateTransactionModel.builder()
                .accountId(request.getAccountId())
                .type(request.getType())
                .amount(request.getAmount())
                .build();
    }

    public static CreatedTransactionResponse toCreatedTransactionResponse(final CreatedTransactionModel model) {
        return CreatedTransactionResponse.builder()
                .id(model.getId())
                .build();
    }
}
