package com.transactions.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationType {

    CASH_PURCHASE(1),
    INSTALLMENT_PURCHASE(2),
    WITHDRAWAL(3),
    PAYMENT(4);

    private final int value;
}
