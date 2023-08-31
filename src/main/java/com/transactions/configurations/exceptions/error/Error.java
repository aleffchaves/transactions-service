package com.transactions.configurations.exceptions.error;

import com.transactions.configurations.exceptions.enums.ErrorType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class Error {

    private final ErrorType code;
    private final String message;
    private final List<ErrorField> fields;
}
