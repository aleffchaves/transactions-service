package com.transactions.interactors.usecases;

import com.transactions.configurations.exceptions.CustomerAlreadyHasAccountException;
import com.transactions.entities.Account;
import com.transactions.providers.AccountsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {

    private static final String DOCUMENT_NUMBER = "14649883741";

    @Mock
    private AccountsProvider accountsProvider;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @InjectMocks
    private CreateAccountUseCase createAccount;

    @Test
    void shouldBeCreateAnAccountWhenCreateAccountUseCaseIsCalled() {

        Mockito.when(this.accountsProvider.checkExistsAccountByDocumentNumber(DOCUMENT_NUMBER))
                .thenReturn(Boolean.FALSE);

        Mockito.doNothing()
                .when(this.accountsProvider).createAccount(Mockito.any());

        final var result = this.createAccount.execute(DOCUMENT_NUMBER);

        Mockito.verify(this.accountsProvider).checkExistsAccountByDocumentNumber(Mockito.any());
        Mockito.verify(this.accountsProvider).createAccount(this.accountArgumentCaptor.capture());
        final var expectedAccount = this.accountArgumentCaptor.getValue();

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(expectedAccount.getDocumentNumber());
        Assertions.assertEquals(expectedAccount.getId(), result.getId());
        Assertions.assertEquals(expectedAccount.getStatus().name(), result.getStatus());
        Assertions.assertEquals(expectedAccount.getNumber(), result.getAccountNumber());
    }

    @Test
    void shouldBeThrowCustomerAlreadyHasAccountExceptionWhenCreateAccountUseCaseIsCalled() {

        Mockito.when(this.accountsProvider.checkExistsAccountByDocumentNumber(Mockito.any()))
                .thenReturn(Boolean.TRUE);

        Assertions.assertThrowsExactly(CustomerAlreadyHasAccountException.class,
                () -> this.createAccount.execute(DOCUMENT_NUMBER));

        Mockito.verify(this.accountsProvider).checkExistsAccountByDocumentNumber(Mockito.any());
        Mockito.verify(this.accountsProvider, Mockito.never()).createAccount(Mockito.any());
    }
}