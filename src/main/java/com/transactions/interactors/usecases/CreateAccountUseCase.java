package com.transactions.interactors.usecases;

import com.transactions.entities.Account;
import com.transactions.entities.enums.StatusAccount;
import com.transactions.interactors.dto.CreatedAccountModel;
import com.transactions.interactors.ports.CreateAccountUseCasePort;
import com.transactions.providers.AccountsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAccountUseCase implements CreateAccountUseCasePort {

    private final AccountsProvider accountsProvider;

    @Override
    public CreatedAccountModel execute(final String documentNumber) {
        log.info("USE CASE - Starting account creation process - documentNumber: " + documentNumber);

        this.checkIfCustomerHasAnAccount(documentNumber);
        final var account = this.buildAccount(documentNumber);
        this.accountsProvider.createAccount(account);

        log.info("USE CASE - Account creation process completed. Account ID: +" + account.getId());

        return this.buildCreatedAccountResponse(account);
    }

    private void checkIfCustomerHasAnAccount(final String documentNumber) {
        if (this.accountsProvider.checkExistsAccountByDocumentNumber(documentNumber)) {
            throw new RuntimeException("Customer already has an account.");
        }
    }

    private String accountNumberGenerate() {
        final var accountNumber = RandomUtils.nextInt(10000, 99999) - 1;
        return String.valueOf(accountNumber);
    }

    private Account buildAccount(final String documentNumber) {
        return Account.builder()
                .id(UUID.randomUUID().toString())
                .number(this.accountNumberGenerate())
                .balance(BigDecimal.TEN)
                .status(StatusAccount.ACTIVE)
                .documentNumber(documentNumber)
                .build();
    }

    private CreatedAccountModel buildCreatedAccountResponse(final Account account) {
        return CreatedAccountModel.builder()
                .id(account.getId())
                .accountNumber(account.getNumber())
                .balance(account.getBalance())
                .status(account.getStatus().name())
                .build();
    }
}
