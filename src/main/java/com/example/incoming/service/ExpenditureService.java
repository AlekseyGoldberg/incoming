package com.example.incoming.service;

import com.example.incoming.entity.Expenditure;
import com.example.incoming.entity.User;
import com.example.incoming.repository.Impl.ExpenditureRepositoryImpl;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class ExpenditureService {
    private final ExpenditureRepositoryImpl expenditureRepositoryImpl;
    private final UserRepositoryImpl userRepository;

    public ExpenditureService(ExpenditureRepositoryImpl expenditureRepositoryImpl, UserRepositoryImpl userRepository) {
        this.expenditureRepositoryImpl = expenditureRepositoryImpl;
        this.userRepository = userRepository;
    }

    public String addExpenditure(JSONObject body, String username) {
        try {
            User user = userRepository.getUser(username);
            int userId = user.getId();
            float amount = body.getFloat("amount");
            String comment = body.getString("comment");
            String dataTransaction = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            expenditureRepositoryImpl.addExpenditure(userId, amount, comment, dataTransaction);

            return "ok";
        } catch (Exception e) {
            return "bad";
        }

    }
}
