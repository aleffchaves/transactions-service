package com.transactions.interactors.usecases;

import com.transactions.entities.Transaction;
import com.transactions.entities.enums.OperationType;
import com.transactions.fixture.accounts.AccountFixture;
import com.transactions.fixture.transactions.CreateTransactionModelFixture;
import com.transactions.interactors.dto.CreateTransactionModel;
import com.transactions.providers.AccountsProvider;
import com.transactions.providers.TransactionsProvider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTransactionUseCaseTest {

    @Mock
    private TransactionsProvider transactionsProvider;

    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptor;

    @Mock
    private AccountsProvider accountsProvider;

    @InjectMocks
    private CreateTransactionUseCase createTransaction;

    @ParameterizedTest
    @MethodSource("transactionTypesProvider")
    void shouldBeCreateATransactionWhenCreateTransactionUseCaseIsCalled(final CreateTransactionModel model) {

        final var account = AccountFixture.createAccount();
        when(this.accountsProvider.findAccountById(anyString())).thenReturn(account);

        final var result = this.createTransaction.execute(model);

        verify(this.transactionsProvider).createTransaction(this.transactionArgumentCaptor.capture());
        verify(this.accountsProvider).findAccountById(anyString());

        final var capturedTransaction = this.transactionArgumentCaptor.getValue();

        assertNotNull(result);
        assertEquals(model.getAccountId().toString(), account.getId());
        assertEquals(model.getAmount().abs(), capturedTransaction.getAmount().abs());
        assertEquals(model.getType(), capturedTransaction.getType());
    }

    private static Stream<CreateTransactionModel> transactionTypesProvider() {
        return Stream.of(
                CreateTransactionModelFixture.createCreateTransactionModelWithOperationType(OperationType.CASH_PURCHASE),
                CreateTransactionModelFixture.createCreateTransactionModelWithOperationType(OperationType.INSTALLMENT_PURCHASE),
                CreateTransactionModelFixture.createCreateTransactionModelWithOperationType(OperationType.WITHDRAWAL),
                CreateTransactionModelFixture.createCreateTransactionModelWithOperationType(OperationType.PAYMENT)
        );
    }
}