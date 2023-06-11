package com.example.incoming.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_invest")
public class Invest {
    public Invest(User user, String nameInvest, double sumInvest, Account account, String dateReturn) {
        this.user = user;
        this.nameInvest = nameInvest;
        this.sumInvest = sumInvest;
        this.account = account;
        this.dateReturn = dateReturn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "name_invest")
    private String nameInvest;
    @Column(name = "sum_invest")
    private double sumInvest;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Column
    private String dateReturn;
}
