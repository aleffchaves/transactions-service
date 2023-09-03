package com.transactions.transportlayers.controllers;

import com.transactions.configurations.exceptions.GlobalExceptionHandler;
import com.transactions.fixture.transactions.CreateTransactionModelFixture;
import com.transactions.fixture.transactions.CreateTransactionRequestFixture;
import com.transactions.fixture.transactions.CreatedTransactionModelFixture;
import com.transactions.interactors.ports.CreateTransactionUseCasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.transactions.utils.JsonConversionUtils.mapToJson;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransactionsControllerTest {

    private static final String TRANSACTIONS_URL_PATH = "/pismo/api/v1/transactions";

    private MockMvc mockMvc;

    @Mock
    CreateTransactionUseCasePort createTransaction;

    @InjectMocks
    TransactionsController transactionsController;

    @BeforeEach
    void beforeEach() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.transactionsController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void whenPOSTIsCalledToCreateATransactionThenStatusCREATEDShouldBeReturned() throws Exception {

        final var createTransactionRequest = CreateTransactionRequestFixture.createTransactionRequest();
        final var createTransactionModel = CreateTransactionModelFixture.createCreateTransactionModel();
        final var createdTransactionModel = CreatedTransactionModelFixture.createdTransactionModel();

        Mockito.when(this.createTransaction.execute(createTransactionModel))
                .thenReturn(createdTransactionModel);

        this.mockMvc.perform(post(TRANSACTIONS_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(createTransactionRequest)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(createdTransactionModel.getId())));
    }
}