package com.example.incoming.repository;

import com.example.incoming.entity.Invest;
import com.example.incoming.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestConnection extends JpaRepository<Invest, Long> {
    List<Invest> findAllByUser(User user);

    Invest findByIdAndUser(Long id, User user);

}
