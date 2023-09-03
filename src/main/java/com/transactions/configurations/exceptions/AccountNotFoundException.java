package com.transactions.configurations.exceptions;

import com.transactions.configurations.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseBusinessException {

    public AccountNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorType.ERROR_CODE_005);
    }
}
