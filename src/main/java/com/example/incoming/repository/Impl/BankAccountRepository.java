package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Account;
import com.example.incoming.repository.BankAccountConnection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@AllArgsConstructor
public class BankAccountRepository {
    private final BankAccountConnection bankAccountConnection;

    @Transactional
    public void saveAccount(Account account) {
        bankAccountConnection.save(account);
    }
}
