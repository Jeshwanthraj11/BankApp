package com.raj.Bank_App.repository;

import com.raj.Bank_App.entity.AccountAndTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountAndTransactionRepository extends JpaRepository<AccountAndTransaction,Long> {

    Optional<List<AccountAndTransaction>> findByAccountNumber(Long accountNumber);
}
