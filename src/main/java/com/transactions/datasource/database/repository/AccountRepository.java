package com.transactions.datasource.database.repository;

import com.transactions.datasource.database.models.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, String> {

    boolean existsByDocumentNumber(final String documentNumber);
}
