package com.example.incoming.service;

import com.example.incoming.entity.User;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepositoryImpl repository;

    public UserService(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public User saveUser(User user){
        return repository.saveUser(user);
    }

    public User getUser(String username){
        return repository.getUser(username);
    }
}
