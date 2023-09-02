package com.transactions.datasource.database.datasources;

import com.transactions.datasource.database.models.TransactionData;
import com.transactions.datasource.database.repository.TransactionRepository;
import com.transactions.entities.Transaction;
import com.transactions.fixture.transactions.TransactionFixture;
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
class TransactionsDataSourceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Captor
    private ArgumentCaptor<TransactionData> transactionDataCaptor;

    @InjectMocks
    private TransactionsDataSource transactionsDataSource;

    @Test
    void whenCreateTransactionIsCalledThenAnTransactionShouldBeCreated() {

        final var transaction = TransactionFixture.createTransaction();
        this.transactionsDataSource.createTransaction(transaction);

        Mockito.verify(this.transactionRepository).save(this.transactionDataCaptor.capture());

        this.transactionAssertions(transaction);
        this.accountAssertions(transaction);
    }

    private void transactionAssertions(final Transaction transaction) {
        final var transactionDataCaptured = this.transactionDataCaptor.getValue();

        Assertions.assertEquals(transaction.getId(), transactionDataCaptured.getId());
        Assertions.assertEquals(transaction.getType(), transactionDataCaptured.getType());
        Assertions.assertEquals(transaction.getEventDate().getHour(), transactionDataCaptured.getEventDate().getHour());
    }

    private void accountAssertions(final Transaction transaction) {
        final var expectedAccount = transaction.getAccount();
        final var actualAccount = this.transactionDataCaptor.getValue().getAccount();

        Assertions.assertEquals(expectedAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(expectedAccount.getStatus(), actualAccount.getStatus());
        Assertions.assertEquals(expectedAccount.getNumber(), actualAccount.getNumber());
        Assertions.assertEquals(expectedAccount.getDocumentNumber(), actualAccount.getDocumentNumber());
    }
}