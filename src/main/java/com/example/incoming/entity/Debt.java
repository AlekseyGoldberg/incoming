package com.example.incoming.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_debt")
public class Debt {
    public Debt(User user, String nameDebt, double sum_debt, Account account, String dateReturn) {
        this.user = user;
        this.nameDebt = nameDebt;
        this.sum_debt = sum_debt;
        this.account = account;
        this.dateReturn = dateReturn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "name_debt")
    private String nameDebt;
    @Column(name = "sum_debt")
    private double sum_debt;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Column(name = "date_return")
    private String dateReturn;
}
