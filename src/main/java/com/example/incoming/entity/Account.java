package com.example.incoming.entity;

import liquibase.pro.packaged.D;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_bank_account")
public class Account {
    public Account(String nameAccount, User user, Double totalAmount) {
        this.nameAccount = nameAccount;
        this.user = user;
        this.totalAmount = totalAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_account")
    private String nameAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @Column(name = "totalAmount")
    private Double totalAmount;
}
