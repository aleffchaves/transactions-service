package com.transactions.datasource.database.models;

import com.transactions.entities.enums.OperationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionData {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountData account;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    private LocalDateTime eventDate;
}
