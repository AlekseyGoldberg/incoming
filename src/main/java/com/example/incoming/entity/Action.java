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
@Table(name = "t_actions")
public class Action {
    public Action(User user, Double amount, String comment, Account account, Category category, String typeOperation) {
        this.user = user;
        this.amount = amount;
        this.comment = comment;
        this.dataTransaction = new Date();
        this.account = account;
        this.category = category;
        this.typeOperation = typeOperation;
    }

    public Action(Long id, User user, Double amount, String comment, Account account, Category category, String typeOperation) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.comment = comment;
        this.dataTransaction = new Date();
        this.account = account;
        this.category = category;
        this.typeOperation = typeOperation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "comment")
    private String comment;
    @Column(name = "date_transaction")
    private Date dataTransaction;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "type_operation")
    private String typeOperation;
}
