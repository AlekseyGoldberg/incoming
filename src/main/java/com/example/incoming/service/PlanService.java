package com.example.incoming.service;

import com.example.incoming.dto.request.PlanReqDto;
import com.example.incoming.entity.Plan;
import com.example.incoming.entity.User;
import com.example.incoming.repository.Impl.PlanRepositoryImpl;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanService {
    private PlanRepositoryImpl planRepository;
    private AuthService authService;
    private UserRepositoryImpl userRepository;

    public void savePlan(String jwt, PlanReqDto dto) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Plan plan = new Plan(user, dto);
        planRepository.savePlan(plan);
    }

    public List<Plan> getPlans(String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        return planRepository.getPlans(user);
    }

    public void completePlan(String jwt, Long id) {
        authService.checkJwt(jwt);
        Plan plan = planRepository.getPlan(id);
        plan.setComplete(true);
        planRepository.savePlan(plan);
    }

    public void deletePlan(String jwt, Long id) {
        authService.checkJwt(jwt);
        planRepository.deletePlan(id);
    }
}
