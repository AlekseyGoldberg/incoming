package com.example.incoming.repository;

import com.example.incoming.entity.Account;
import com.example.incoming.entity.User;
import liquibase.pro.packaged.S;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountConnection extends JpaRepository<Account, Long> {
    boolean existsAccountByNameAccount(String nameAccount);

    Account findByUserAndNameAccount(User user,String nameAccount);
}
