package com.transactions.datasource.database.repository;

import com.transactions.datasource.database.models.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionData, String> {
}
