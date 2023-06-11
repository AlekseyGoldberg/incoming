package com.example.incoming.repository;

import com.example.incoming.entity.Plan;
import com.example.incoming.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanConnection extends JpaRepository<Plan, Long> {
    List<Plan> findAllByUser(User user);
}
