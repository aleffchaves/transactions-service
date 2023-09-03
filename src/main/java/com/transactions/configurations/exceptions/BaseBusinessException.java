package com.transactions.configurations.exceptions;

import com.transactions.configurations.exceptions.enums.ErrorType;
import com.transactions.configurations.exceptions.error.Error;
import com.transactions.configurations.exceptions.error.StandardError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
public abstract class BaseBusinessException extends RuntimeException {

    private final HttpStatus status;
    private final transient List<Error> errors;
    private final ErrorType code;

    protected BaseBusinessException(final HttpStatus status, final ErrorType code) {
        this.status = status;
        this.code = code;
        this.errors = new ArrayList<>();
    }

    public final StandardError getStandardError(final String path, final Locale messageLocale) {
        return StandardError.builder()
                .path(path)
                .status(this.status.value())
                .error(this.buildErrorDataList(messageLocale))
                .timestamp(LocalDateTime.now())
                .build();
    }

    protected Error buildErrorDataList(final Locale messageLocale) {
        return Error.builder()
                .code(this.code)
                .message(this.code.getMessage(messageLocale))
                .build();
    }
}
