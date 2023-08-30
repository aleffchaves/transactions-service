package com.transactions.datasource.database.datasources;

import com.transactions.datasource.database.converter.AccountConverter;
import com.transactions.datasource.database.repository.AccountRepository;
import com.transactions.entities.Account;
import com.transactions.providers.AccountsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountsDataSource implements AccountsProvider {

    private final AccountRepository accountRepository;

    @Override
    public boolean checkExistsAccountByDocumentNumber(final String documentNumber) {
        log.info("Searching account for document: " + documentNumber);
        return this.accountRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public void createAccount(final Account account) {
        log.info("Saving account");
        final var accountData = AccountConverter.toAccountData(account);
        this.accountRepository.save(accountData);
    }
}
