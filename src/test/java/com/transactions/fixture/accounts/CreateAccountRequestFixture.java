package com.transactions.fixture.accounts;

import com.transactions.transportlayers.dto.CreateAccountRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateAccountRequestFixture {

    public static CreateAccountRequest createAccountRequest() {
        return CreateAccountRequest.builder()
                .documentNumber("13785807295")
                .build();
    }

}
