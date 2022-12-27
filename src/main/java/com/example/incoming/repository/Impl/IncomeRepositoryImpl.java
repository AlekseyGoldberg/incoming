package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Income;
import com.example.incoming.entity.TotalAmount;
import com.example.incoming.repository.IncomeTableConnection;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IncomeRepositoryImpl {
    private final IncomeTableConnection incomeTableConnection;
    private final TotalAmountRepository totalAmountRepository;

    public IncomeRepositoryImpl(IncomeTableConnection incomeTableConnection, TotalAmountRepository totalAmountRepository) {
        this.incomeTableConnection = incomeTableConnection;
        this.totalAmountRepository = totalAmountRepository;
    }

    public String addIncome(int userId, float amount, String comment, String dataTransaction) {
        TotalAmount totalAmountEntity = totalAmountRepository.getTotalIncome(userId);
        float totalIncome = 0;
        if (Optional.ofNullable(totalAmountEntity).isEmpty()) {
            totalIncome += amount;
            totalAmountRepository.saveTotalIncome(userId, totalIncome);
        } else {
            totalIncome = totalAmountEntity.getTotalIncome() + amount;
            totalAmountRepository.updateTotalIncome(userId, totalIncome);
        }
        Income income = new Income(userId, amount, comment, dataTransaction);
        incomeTableConnection.save(income);
        return "ok";
    }
}
