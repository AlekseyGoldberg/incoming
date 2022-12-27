package com.example.incoming.repository.Impl;


import com.example.incoming.entity.Income;
import com.example.incoming.entity.User;
import com.example.incoming.repository.UserTableConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl {
    private final com.example.incoming.repository.UserTableConnection UserTableConnection;

    public UserRepositoryImpl(com.example.incoming.repository.UserTableConnection userTableConnection) {
        UserTableConnection = userTableConnection;
    }

    public User getUser(String login) {
        return UserTableConnection.findByLogin(login);
    }

    public User saveUser(User user){
        return UserTableConnection.save(user);
    }
}
