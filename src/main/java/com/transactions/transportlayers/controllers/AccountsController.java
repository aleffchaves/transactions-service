package com.transactions.transportlayers.controllers;

import com.transactions.interactors.ports.AccountInfoUseCasePort;
import com.transactions.interactors.ports.CreateAccountUseCasePort;
import com.transactions.transportlayers.converters.AccountInfoConverter;
import com.transactions.transportlayers.converters.CreateAccountConverter;
import com.transactions.transportlayers.dto.AccountInfoResponse;
import com.transactions.transportlayers.dto.CreateAccountRequest;
import com.transactions.transportlayers.dto.CreatedAccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Accounts")
@RestController
@RequestMapping("pismo/api/v1/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final CreateAccountUseCasePort createAccount;
    private final AccountInfoUseCasePort accountInfo;

    @PostMapping
    @Operation(summary = "Create an account", description = "This operation creates an account.")
    public ResponseEntity<CreatedAccountResponse> createAccount(@Valid @RequestBody final CreateAccountRequest request) {
        log.info("TRANSPORT LAYER  - createAccount - received create account request. - DOCUMENT_NUMBER: {}", request.getDocumentNumber());

        final var responseModel = this.createAccount.execute(request.getDocumentNumber());

        log.info("TRANSPORT LAYER - createAccount - Create account performed successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateAccountConverter.toCreatedAccountResponse(responseModel));
    }

    @GetMapping("{accountId}")
    @Operation(summary = "Get account details", description = "This operation get account information.")
    public ResponseEntity<AccountInfoResponse> getAccountInfo(@PathVariable final String accountId) {
        log.info("TRANSPORT LAYER - getAccountInfo - received get account info request. ID: " + accountId);

        final var responseModel = this.accountInfo.execute(accountId);

        log.info("TRANSPORT LAYER - getAccountInfo - account info performed successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(AccountInfoConverter.toAccountInfoResponse(responseModel));
    }
}
