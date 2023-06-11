package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Account;
import com.example.incoming.entity.QAccount;
import com.example.incoming.entity.User;
import com.example.incoming.repository.AccountConnection;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountRepositoryImpl {
    private AccountConnection accountConnection;
    private EntityManager entityManager;

    @Transactional
    public List<String> getAccountNames(User user) {
        JPAQuery<Account> query = new JPAQuery<>(entityManager);
        QAccount account = QAccount.account;
        return query.select(account.nameAccount).from(account)
                .where(account.user.eq(user))
                .fetch();
    }

    @Transactional
    public void saveAccount(Account account) {
        accountConnection.save(account);
    }

    @Transactional
    public boolean existAccount(String account) {
        return accountConnection.existsAccountByNameAccount(account);
    }

    @Transactional
    public Account getAllAccounts(User user, String nameAccount) {
        return accountConnection.findByUserAndNameAccount(user, nameAccount);
    }

    public List<Tuple> getAllAccounts(User user) {
        JPAQuery<Account> query = new JPAQuery<>(entityManager);
        QAccount account = QAccount.account;
        return query.select(account.id, account.nameAccount, account.totalAmount).from(account)
                .where(account.user.eq(user)).fetch();
    }

    public Double getTotalAmount(User user) {
        JPAQuery<Account> query = new JPAQuery<>(entityManager);
        QAccount account = QAccount.account;

        return query.select(account.totalAmount.sum()).from(account)
                .where(account.user.eq(user)).fetch().get(0);
    }

    public Account getAccountByName(User user, String accountName) {
        return accountConnection.findByUserAndNameAccount(user, accountName);
    }
}
