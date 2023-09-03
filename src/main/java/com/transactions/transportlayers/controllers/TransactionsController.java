package com.transactions.transportlayers.controllers;

import com.transactions.interactors.ports.CreateTransactionUseCasePort;
import com.transactions.transportlayers.converters.TransactionConverter;
import com.transactions.transportlayers.dto.CreateTransactionRequest;
import com.transactions.transportlayers.dto.CreatedTransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Transactions")
@RestController
@RequestMapping("pismo/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final CreateTransactionUseCasePort createTransaction;

    @PostMapping
    @Operation(summary = "Create a transaction", description = "This operation creates a transaction.")
    public ResponseEntity<CreatedTransactionResponse> createTransaction(@Valid @RequestBody final CreateTransactionRequest request) {
        log.info("TRANSPORT LAYER - createTransaction - REQUEST: " + request);

        final var requestModel = TransactionConverter.toCreateTransactionModel(request);

        final var responseModel = this.createTransaction.execute(requestModel);

        log.info("TRANSPORT LAYER - createTransaction - created transaction id: " + responseModel.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.toCreatedTransactionResponse(responseModel));
    }
}
