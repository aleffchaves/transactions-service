package com.transactions.datasource.repository;

import com.transactions.datasource.models.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, String> {

    boolean existsByDocumentNumber(final String documentNumber);
}
