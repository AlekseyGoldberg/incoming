package com.example.incoming.controller;

import com.example.incoming.entity.Expenditure;
import com.example.incoming.entity.Income;
import com.example.incoming.entity.User;
import com.example.incoming.service.ExpenditureService;
import com.example.incoming.service.IncomingService;
import com.example.incoming.service.UserService;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping
@RestController
public class MyController {
    private final UserService userService;
    private final IncomingService incomingService;
    private final ExpenditureService expenditureService;

    public MyController(UserService userService, IncomingService incomingService,ExpenditureService expenditureService) {
        this.userService = userService;
        this.incomingService = incomingService;
        this.expenditureService=expenditureService;
    }

    @GetMapping("/")
    public String testRequest() {
        return "hello";
    }

    @PostMapping("/registry")
    public User registry(HttpEntity<String> request) {
        JSONObject body = new JSONObject(request.getBody());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(body.getString("login"), passwordEncoder.encode(body.getString("password")));
        return userService.saveUser(user);
    }

    @PostMapping("/income")
    public String saveIncome(HttpEntity<String> request, Principal principal) {
        JSONObject body = new JSONObject(request.getBody());
        String username = principal.getName();
        String status= incomingService.addIncome(body, username);
        return status;
    }

    @PostMapping("/expenditure")
    public String saveExpenditure(HttpEntity<String> request,Principal principal){
        JSONObject body=new JSONObject(request.getBody());
        String username= principal.getName();
        return expenditureService.addExpenditure(body,username);
    }
}
