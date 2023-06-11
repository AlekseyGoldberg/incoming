package com.example.incoming.repository;

import com.example.incoming.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountConnection extends JpaRepository<Account,Long> {
}
