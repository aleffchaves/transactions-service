package com.transactions.datasource.database.datasources;

import com.transactions.configurations.exceptions.AccountNotFoundException;
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
    public Account findAccountById(final String accountId) {
        log.info("DATA SOURCE - Searching account - ACCOUNT ID: " + accountId);
        return this.accountRepository.findById(accountId)
                .map(AccountConverter::toAccount)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public void createAccount(final Account account) {
        log.info("DATA SOURCE - Saving account - ACCOUNT ID: " + account.getId());
        final var accountData = AccountConverter.toAccountData(account);
        this.accountRepository.save(accountData);
    }
}
