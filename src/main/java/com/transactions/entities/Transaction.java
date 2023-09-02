package com.transactions.entities;

import com.transactions.entities.enums.OperationType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {

    @EqualsAndHashCode.Include
    private String id;
    private Account account;
    private BigDecimal amount;
    private OperationType type;
    private LocalDateTime eventDate;
}