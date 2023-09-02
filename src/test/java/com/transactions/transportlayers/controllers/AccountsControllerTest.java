package com.transactions.transportlayers.controllers;

import com.transactions.configurations.exceptions.GlobalExceptionHandler;
import com.transactions.fixture.accounts.AccountInfoModelFixture;
import com.transactions.fixture.accounts.CreateAccountRequestFixture;
import com.transactions.fixture.accounts.CreatedAccountModelFixture;
import com.transactions.interactors.usecases.AccountInfoUseCase;
import com.transactions.interactors.usecases.CreateAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.transactions.utils.JsonConversionUtils.mapToJson;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class AccountsControllerTest {

    private static final String ACCOUNTS_URL_PATH = "/pismo/api/v1/accounts";
    private static final String ACCOUNT_ID_PATH_VARIABLE = "/d3fc103c-2fbf-4192-9e5d-b131916d4be0";
    private static final String ACCOUNT_ID_REQUEST = "d3fc103c-2fbf-4192-9e5d-b131916d4be0";

    private MockMvc mockMvc;

    @Mock
    private CreateAccountUseCase createAccountUseCase;

    @Mock
    private AccountInfoUseCase accountInfoUseCase;

    @InjectMocks
    private AccountsController accountsController;

    @BeforeEach
    void beforeEach() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.accountsController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void whenPOSTIsCalledToCreateAnAccountThenStatusCREATEDShouldBeReturned() throws Exception {

        final var createAccountRequest = CreateAccountRequestFixture.createAccountRequest();
        final var createdAccountModel = CreatedAccountModelFixture.createCreatedAccountModel();

        Mockito.when(this.createAccountUseCase.execute(createAccountRequest.getDocumentNumber()))
                .thenReturn(createdAccountModel);

        this.mockMvc.perform(MockMvcRequestBuilders.post(ACCOUNTS_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(createAccountRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(createdAccountModel.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber", is(createdAccountModel.getAccountNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(createdAccountModel.getStatus())));
    }

    @Test
    void whenPOSTIsCalledToGetAccountInfoThenStatusOKShouldBeReturned() throws Exception {

        final var createdAccountInfoModel = AccountInfoModelFixture.createAccountInfoModel();

        Mockito.when(this.accountInfoUseCase.execute(ACCOUNT_ID_REQUEST))
                .thenReturn(createdAccountInfoModel);

        this.mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNTS_URL_PATH.concat(ACCOUNT_ID_PATH_VARIABLE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(createdAccountInfoModel.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber", is(createdAccountInfoModel.getAccountNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentNumber", is(createdAccountInfoModel.getDocumentNumber())));
    }
}