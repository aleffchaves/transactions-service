package com.transactions.transportlayers;

import com.transactions.interactors.ports.AccountInfoUseCasePort;
import com.transactions.interactors.ports.CreateAccountUseCasePort;
import com.transactions.transportlayers.controller.converters.AccountInfoConverter;
import com.transactions.transportlayers.controller.converters.CreateAccountConverter;
import com.transactions.transportlayers.controller.dto.AccountInfoResponse;
import com.transactions.transportlayers.controller.dto.CreateAccountRequest;
import com.transactions.transportlayers.controller.dto.CreatedAccountResponse;
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
@RestController
@RequestMapping("/api/transactions-payments")
@RequiredArgsConstructor
public class TransactionsPaymentsController {

    private final CreateAccountUseCasePort createAccount;
    private final AccountInfoUseCasePort accountInfo;

    @PostMapping("v1/accounts")
    public ResponseEntity<CreatedAccountResponse> createAccount(@Valid @RequestBody final CreateAccountRequest request) {
        log.info("TRANSPORT LAYER  - received create account request.");

        final var responseModel = this.createAccount.execute(request.getDocumentNumber());

        log.info("TRANSPORT LAYER - Create account performed successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateAccountConverter.toCreatedAccountResponse(responseModel));
    }

    @GetMapping("v1/accounts/{accountId}")
    public ResponseEntity<AccountInfoResponse> getAccountInfo(@PathVariable final String accountId) {
        log.info("TRANSPORT LAYER - received get account info request. ID: " + accountId);

        final var responseModel = this.accountInfo.execute(accountId);

        log.info("TRANSPORT LAYER - account info performed successfully.");

        return ResponseEntity.status(HttpStatus.OK).body(AccountInfoConverter.toAccountInfoResponse(responseModel));
    }
}
