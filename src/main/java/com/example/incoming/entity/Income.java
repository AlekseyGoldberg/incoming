package com.example.incoming.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Income {

    public Income(int userId, float amount, String comment, String dataTransaction) {
        this.userId = userId;
        this.amount = amount;
        this.comment = comment;
        this.dataTransaction = dataTransaction;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int userId;
    @Column
    private float amount;
    @Column
    private String comment;
    @Column()
    private String dataTransaction;

    public boolean isEmpry() {
        return comment == null && dataTransaction == null;

    }

}
