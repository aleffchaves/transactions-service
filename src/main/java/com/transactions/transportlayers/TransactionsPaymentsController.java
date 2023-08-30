package com.transactions.transportlayers;

import com.transactions.interactors.ports.CreateAccountPort;
import com.transactions.transportlayers.controller.converters.CreateAccountConverter;
import com.transactions.transportlayers.controller.dto.CreateAccountRequest;
import com.transactions.transportlayers.controller.dto.CreatedAccountResponse;
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
@RestController
@RequestMapping("/api/v1/transactions-payments")
@RequiredArgsConstructor
public class TransactionsPaymentsController {

    private final CreateAccountPort createAccount;

    @PostMapping("/accounts")
    public ResponseEntity<CreatedAccountResponse> createAccount(@Valid @RequestBody final CreateAccountRequest request) {
        log.info("received create account request.");

        final var responseModel = this.createAccount.execute(request.getDocumentNumber());

        log.info("Create account performed successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateAccountConverter.toCreatedAccountResponse(responseModel));
    }
}
