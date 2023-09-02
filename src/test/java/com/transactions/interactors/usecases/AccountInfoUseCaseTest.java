package com.transactions.interactors.usecases;

import com.transactions.fixture.accounts.AccountFixture;
import com.transactions.providers.AccountsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountInfoUseCaseTest {

    private final static String ACCOUNT_ID = "3927a7c8-6a2a-4f14-91e7-e3582e000456";

    @Mock
    AccountsProvider accountsProvider;

    @InjectMocks
    AccountInfoUseCase accountInfo;

    @Test
    void shouldBeReturnAccountInformationWhenAccountInfoUseCaseIsCalled() {

        final var account = AccountFixture.createAccount();

        Mockito.when(this.accountsProvider.findAccountById(ACCOUNT_ID))
                .thenReturn(account);

        final var result = this.accountInfo.execute(ACCOUNT_ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(ACCOUNT_ID, result.getId());
        Assertions.assertNotNull(result.getStatus());
        Assertions.assertNotNull(result.getAccountNumber());
        Assertions.assertNotNull(result.getDocumentNumber());
    }
}