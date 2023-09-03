package com.transactions.interactors.ports;

import com.transactions.interactors.dto.CreatedAccountModel;

public interface CreateAccountUseCasePort {
    CreatedAccountModel execute(final String documentNumber);
}
