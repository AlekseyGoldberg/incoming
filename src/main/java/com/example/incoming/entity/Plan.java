package com.example.incoming.entity;

import com.example.incoming.dto.request.PlanReqDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_plans")
public class Plan {
    public Plan(User user, PlanReqDto dto) {
        this.user = user;
        this.namePlan = dto.getName();
        this.sumPlan = Double.parseDouble(dto.getSum());
        this.dateComplete = dto.getDate();
        this.dateInit = new Date();
        this.isComplete = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "name_plan")
    private String namePlan;
    @Column(name = "sum_plan")
    private double sumPlan;
    @Column(name = "date_complete")
    private String dateComplete;
    @Column(name = "date_init")
    private Date dateInit;
    @Column(name = "is_complete")
    private boolean isComplete;
}
