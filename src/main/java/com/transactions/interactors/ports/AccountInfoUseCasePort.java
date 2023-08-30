package com.transactions.interactors.ports;

import com.transactions.interactors.dto.AccountInfoModel;

public interface AccountInfoUseCasePort {
    AccountInfoModel execute(final String accountId);
}
