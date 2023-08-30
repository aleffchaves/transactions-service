package com.transactions.interactors.ports;

import com.transactions.interactors.dto.CreatedAccountModel;

public interface CreateAccountInputPort {
    CreatedAccountModel execute(final String documentNumber);
}
