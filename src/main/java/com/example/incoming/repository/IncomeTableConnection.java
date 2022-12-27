package com.example.incoming.repository;

import com.example.incoming.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeTableConnection extends JpaRepository<Income,Integer> {
}
