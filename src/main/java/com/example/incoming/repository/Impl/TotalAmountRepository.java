package com.example.incoming.repository.Impl;

import com.example.incoming.entity.TotalAmount;
import com.example.incoming.repository.TotalAmountConnection;
import org.springframework.stereotype.Repository;

@Repository
public class TotalAmountRepository {
    private final TotalAmountConnection totalAmountConnection;

    public TotalAmountRepository(TotalAmountConnection totalAmountConnection) {
        this.totalAmountConnection = totalAmountConnection;
    }

    public TotalAmount updateTotalIncome(int userId, float totalIncome) {
        return totalAmountConnection.updateTotalIncome(userId, totalIncome);
    }

    public TotalAmount getTotalIncome(int userId) {
        return totalAmountConnection.getTotalIncome(userId);
    }

    public TotalAmount saveTotalIncome(int userId, float income) {
        TotalAmount totalAmount = new TotalAmount(userId, 0, income);
        return totalAmountConnection.save(totalAmount);
    }

    public TotalAmount getTotalExpenditure(int userId) {
        return totalAmountConnection.getTotalExpenditure(userId);
    }

    public TotalAmount saveTotalExpenditure(int userId, float expenditure) {
        TotalAmount totalAmount = new TotalAmount(userId, expenditure, 0);
        return totalAmountConnection.save(totalAmount);
    }

    public TotalAmount updateTotalExpenditure(int userId, float totalExpenditure) {
        return totalAmountConnection.updateTotalExpenditure(userId, totalExpenditure);
    }
}