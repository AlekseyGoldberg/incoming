package com.example.incoming.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expenditure {
    public Expenditure(int userId, float amount, String comment, String dataTransaction) {
        this.userId = userId;
        this.amount = amount;
        this.comment = comment;
        this.dataTransaction = dataTransaction;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column()
    private int userId;
    @Column
    private float amount;
    @Column
    private String comment;
    @Column(name = "dataTransaction")
    private String dataTransaction;
}
