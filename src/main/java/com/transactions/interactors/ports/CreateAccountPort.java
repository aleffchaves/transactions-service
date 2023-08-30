package com.transactions.interactors.ports;

import com.transactions.interactors.dto.CreatedAccountModel;

public interface CreateAccountPort {
    CreatedAccountModel execute(final String documentNumber);
}
