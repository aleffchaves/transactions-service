package com.transactions.datasource.database.models;

import com.transactions.entities.enums.StatusAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountData {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    private String documentNumber;

    private String number;

    private BigDecimal balance;
}
