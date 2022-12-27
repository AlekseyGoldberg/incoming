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
public class User {
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String login;
    @Column
    private String password;
}
