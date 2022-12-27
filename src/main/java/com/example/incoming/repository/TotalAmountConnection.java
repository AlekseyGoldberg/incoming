package com.example.incoming.repository;

import com.example.incoming.entity.TotalAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TotalAmountConnection extends JpaRepository<TotalAmount, Integer> {
    @Query(value = "UPDATE TotalAmount set totalIncome = ?2 where(userId = ?1)")
    TotalAmount updateTotalIncome(int userId, float income);

    @Query(value = "UPDATE TotalAmount set totalExpenditure = ?2 where(userId = ?1)")
    TotalAmount updateTotalExpenditure(int userId, float expenditure);

    @Query(value = "SELECT totalIncome from TotalAmount WHERE (userId =?1)")
    TotalAmount getTotalIncome(int userId);

    @Query(value = "SELECT totalExpenditure from TotalAmount WHERE (userId =?1)")
    TotalAmount getTotalExpenditure(int userId);
}
