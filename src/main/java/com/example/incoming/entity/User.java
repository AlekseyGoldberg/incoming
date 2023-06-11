package com.example.incoming.entity;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

import javax.persistence.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
@RequestScope
@Entity
public class User {
    public User(String login, String hashPassword, String jwt) {
        this.login = login;
        this.hashPassword = hashPassword;
        this.jwt = jwt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "hash_password")
    private String hashPassword;
    @Column(name = "jwt")
    private String jwt;
    @Column(name = "login")
    private String login;
}
