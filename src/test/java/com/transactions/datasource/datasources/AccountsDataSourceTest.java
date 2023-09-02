package com.transactions.datasource.datasources;

import com.transactions.configurations.exceptions.AccountNotFoundException;
import com.transactions.datasource.models.AccountData;
import com.transactions.datasource.repository.AccountRepository;
import com.transactions.fixture.accounts.AccountDataFixture;
import com.transactions.fixture.accounts.AccountFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountsDataSourceTest {

    private static final String DOCUMENT_NUMBER = "32575541336";
    private static final String ACCOUNT_ID = "3927a7c8-6a2a-4f14-91e7-e3582e000456";

    @Mock
    private AccountRepository accountRepository;

    @Captor
    private ArgumentCaptor<AccountData> accountDataArgumentCaptor;

    @InjectMocks
    private AccountsDataSource accountsDataSource;

    @Test
    void whenCheckIfAccountExistsByDocumentNumberIsCalledThenTRUEShouldBeReturned() {

        Mockito.when(this.accountRepository.existsByDocumentNumber(Mockito.any()))
                .thenReturn(true);

        final var result = this.accountsDataSource.checkExistsAccountByDocumentNumber(DOCUMENT_NUMBER);

        Assertions.assertTrue(result);
        Mockito.verify(this.accountRepository).existsByDocumentNumber(Mockito.any());
    }

    @Test
    void whenFindAccountByIdIsCalledThenItShouldBeReturned() {

        final var accountData = AccountDataFixture.createAccountData();

        Mockito.when(this.accountRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(accountData));

        final var result = this.accountsDataSource.findAccountById(ACCOUNT_ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(ACCOUNT_ID, result.getId());
        Assertions.assertEquals(accountData.getStatus(), result.getStatus());
        Assertions.assertEquals(accountData.getDocumentNumber(), result.getDocumentNumber());
        Mockito.verify(this.accountRepository).findById(Mockito.any());
    }

    @Test
    void whenInvalidAccountIdIsInformedThenAccountNotFoundExceptionShouldBeThrow() {

        Mockito.when(this.accountRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class, () -> this.accountsDataSource.findAccountById(ACCOUNT_ID));

        Mockito.verify(this.accountRepository).findById(Mockito.any());
    }

    @Test
    void whenCreateAccountIsCalledThenAnAccountShouldBeCreated() {

        final var account = AccountFixture.createAccount();
        this.accountsDataSource.createAccount(account);

        Mockito.verify(this.accountRepository).save(this.accountDataArgumentCaptor.capture());

        final var accountDataCaptured = this.accountDataArgumentCaptor.getValue();

        Assertions.assertEquals(account.getId(), accountDataCaptured.getId());
        Assertions.assertEquals(account.getNumber(), accountDataCaptured.getNumber());
        Assertions.assertEquals(account.getDocumentNumber(), accountDataCaptured.getDocumentNumber());
        Assertions.assertEquals(account.getStatus(), accountDataCaptured.getStatus());
    }
}