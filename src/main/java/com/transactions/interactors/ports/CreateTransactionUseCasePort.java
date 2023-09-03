package com.transactions.interactors.ports;

import com.transactions.interactors.dto.CreateTransactionModel;
import com.transactions.interactors.dto.CreatedTransactionModel;

public interface CreateTransactionUseCasePort {

    CreatedTransactionModel execute(final CreateTransactionModel model);
}
