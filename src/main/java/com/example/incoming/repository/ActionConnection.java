package com.example.incoming.repository;

import com.example.incoming.entity.Action;
import com.example.incoming.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionConnection extends JpaRepository<Action, Integer> {
    List<Action> findAllByUser(User user);

    Action findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);
}
