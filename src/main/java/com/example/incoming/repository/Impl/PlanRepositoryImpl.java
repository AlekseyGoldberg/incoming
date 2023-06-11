package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Plan;
import com.example.incoming.entity.User;
import com.example.incoming.repository.PlanConnection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PlanRepositoryImpl {
    private PlanConnection planConnection;

    public void savePlan(Plan plan) {
        planConnection.save(plan);
    }

    public List<Plan> getPlans(User user) {
        return planConnection.findAllByUser(user);
    }

    public Plan getPlan(Long id) {
        return planConnection.getById(id);
    }

    public void deletePlan(Long id) {
        planConnection.deleteById(id);
    }
}
