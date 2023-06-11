package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Action;
import com.example.incoming.entity.User;
import com.example.incoming.repository.ActionConnection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ActionRepositoryImpl {
    private final ActionConnection actionConnection;

    public void saveAction(Action action) {
        actionConnection.save(action);
    }

    public List<Action> getActions(User user) {
        return actionConnection.findAllByUser(user);
    }

    public Action getAction(Long id, User user) {
        return actionConnection.findByIdAndUser(id, user);
    }

    public void deleteAction(Long id, User user) {
        actionConnection.deleteByIdAndUser(id, user);
    }


}
