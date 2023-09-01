package com.transactions.interactors.usecases;

import com.transactions.entities.Account;
import com.transactions.interactors.dto.AccountInfoModel;
import com.transactions.interactors.ports.AccountInfoUseCasePort;
import com.transactions.providers.AccountsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountInfoUseCase implements AccountInfoUseCasePort {

    private final AccountsProvider accountsProvider;

    @Override
    public AccountInfoModel execute(final String accountId) {
        log.info("USE CASE - execute - recovering account info - ID: " + accountId);

        final var account = this.accountsProvider.getAccountInfo(accountId);

        return this.buildAccountInfoModel(account);
    }

    private AccountInfoModel buildAccountInfoModel(final Account account) {
        return AccountInfoModel.builder()
                .status(account.getStatus().name())
                .documentNumber(account.getDocumentNumber())
                .balance(account.getBalance())
                .accountNumber(account.getNumber())
                .build();
    }
}
