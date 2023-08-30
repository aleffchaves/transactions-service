package com.transactions.entities;

import com.transactions.entities.enums.StatusAccount;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account {

    @EqualsAndHashCode.Include
    private String id;
    private StatusAccount status;
    private String documentNumber;
    private String number;
    private BigDecimal balance;
}
