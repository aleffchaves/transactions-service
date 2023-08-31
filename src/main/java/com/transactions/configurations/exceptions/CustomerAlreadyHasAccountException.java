package com.transactions.configurations.exceptions;

import com.transactions.configurations.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class CustomerAlreadyHasAccountException extends BaseBusinessException {

    public CustomerAlreadyHasAccountException() {
        super(HttpStatus.CONFLICT, ErrorType.ERROR_CODE_004);
    }
}
