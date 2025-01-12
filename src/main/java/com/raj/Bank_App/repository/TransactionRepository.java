package com.raj.Bank_App.repository;

import com.raj.Bank_App.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    public Optional<List<Transaction>> findByFromAccountNumber(Long fromAccountNumber);
}
