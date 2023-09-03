package com.transactions.configurations.exceptions.error;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class StandardError {

    private final String path;
    private final Integer status;
    private final LocalDateTime timestamp;
    private final Error error;
}
