package com.example.incoming.repository;

import com.example.incoming.entity.Debt;
import com.example.incoming.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DebtConnection extends JpaRepository<Debt, Long> {
    List<Debt> findAllByUser(User user);

    Debt findByUserAndId(User user, Long id);

    void deleteByUserAndId(User user,Long id);
}
