package com.example.incoming.entity;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
//@Table(name = "total_a  mount")
public class TotalAmount {
    public TotalAmount(int userId, float totalExpenditure, float totalIncome) {
        this.userId = userId;
        this.totalIncome = totalIncome;
        this.totalExpenditure = totalExpenditure;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int userId;
    @Column
    private float totalIncome;
    @Column
    private float totalExpenditure;
}
