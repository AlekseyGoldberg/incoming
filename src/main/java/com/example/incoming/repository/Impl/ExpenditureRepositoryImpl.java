package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Expenditure;
import com.example.incoming.entity.TotalAmount;
import com.example.incoming.repository.ExpenditureConnection;
import com.example.incoming.repository.TotalAmountConnection;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ExpenditureRepositoryImpl {
    private final ExpenditureConnection expenditureConnection;
    private final TotalAmountRepository totalAmountRepository;

    public ExpenditureRepositoryImpl(ExpenditureConnection expenditureConnection, TotalAmountRepository totalAmountConnection) {
        this.expenditureConnection = expenditureConnection;
        this.totalAmountRepository = totalAmountConnection;
    }

    public String addExpenditure(int userId, float amount, String comment, String dateTransaction) {
        TotalAmount totalAmount = totalAmountRepository.getTotalExpenditure(userId);
        float totalExpenditure = 0;
        if (Optional.ofNullable(totalAmount).isEmpty()) {
            totalExpenditure += amount;
            totalAmountRepository.saveTotalExpenditure(userId, totalExpenditure);
        } else {
            totalExpenditure = totalAmount.getTotalExpenditure() + amount;
            totalAmountRepository.updateTotalExpenditure(userId, totalExpenditure);
        }
        Expenditure expenditure = new Expenditure(userId, amount, comment, dateTransaction);
        expenditureConnection.save(expenditure);
        return "ok";
    }
}
