package com.example.incoming.repository.Impl;


import com.example.incoming.entity.User;
import com.example.incoming.repository.UserTableConnection;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class UserRepositoryImpl {
    private final UserTableConnection userTableConnection;

    public UserRepositoryImpl(UserTableConnection userTableConnection) {
        this.userTableConnection = userTableConnection;
    }

    @Transactional
    public User getUser(String login) {
        return userTableConnection.findByLogin(login);
    }

    @Transactional
    public boolean existUser(String login) {
        return userTableConnection.existsUserByLogin(login);
    }

    @Transactional
    public User saveUser(User user) {
        return userTableConnection.save(user);
    }
}
