package com.example.incoming.repository;

import com.example.incoming.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTableConnection extends JpaRepository<User, Long> {
    User findByLogin(String login);

    boolean existsUserByLogin(String login);
}
