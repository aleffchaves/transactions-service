package com.transactions.datasource.repository;

import com.transactions.datasource.models.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionData, String> {
}
