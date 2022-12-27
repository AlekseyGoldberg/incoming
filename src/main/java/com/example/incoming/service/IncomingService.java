package com.example.incoming.service;

import com.example.incoming.entity.Income;
import com.example.incoming.entity.User;
import com.example.incoming.repository.Impl.IncomeRepositoryImpl;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class IncomingService {
    private final IncomeRepositoryImpl incomeRepository;
    private final UserRepositoryImpl userRepository;

    public IncomingService(IncomeRepositoryImpl incomeRepository, UserRepositoryImpl userRepository) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    public String addIncome(JSONObject body, String username) {
        try {
            User user = userRepository.getUser(username);
            int userId = user.getId();
            float amount = body.getFloat("amount");
            String comment = body.getString("comment");
            String dataTransaction = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

            return incomeRepository.addIncome(userId,amount,comment,dataTransaction);
        } catch (Exception e) {
            System.out.println(e);
            return "bad";
        }
    }
}
