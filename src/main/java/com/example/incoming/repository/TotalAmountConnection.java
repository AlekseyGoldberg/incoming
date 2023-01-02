package com.example.incoming.repository;

import com.example.incoming.entity.TotalAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface TotalAmountConnection extends JpaRepository<TotalAmount, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE TotalAmount set totalIncome = ?2 where(userId = ?1)")
    void updateTotalIncome(int userId, float income);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TotalAmount set totalExpenditure = ?2 where(userId = ?1)")
    void updateTotalExpenditure(int userId, float expenditure);

    @Query(value = "SELECT totalIncome from TotalAmount WHERE (userId =?1)")
    float getTotalIncome(int userId);

    @Query(value = "SELECT totalExpenditure from TotalAmount WHERE (userId =?1)")
    TotalAmount getTotalExpenditure(int userId);
}
