package com.example.incoming.dto.response;

import com.example.incoming.entity.Plan;
import lombok.Data;

@Data
public class PlansRespDto {
    public PlansRespDto(Plan plan) {
        this.id = String.valueOf(plan.getId());
        this.namePlan = plan.getNamePlan();
        this.sumPlan = String.valueOf(plan.getSumPlan());
        this.dateComplete = plan.getDateComplete();
        this.isComplete = plan.isComplete();
    }

    private String id;
    private String namePlan;
    private String sumPlan;
    private String dateComplete;
    private boolean isComplete;
}
