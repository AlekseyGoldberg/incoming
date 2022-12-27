package com.example.incoming.repository;

import com.example.incoming.entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureConnection extends JpaRepository<Expenditure,Integer> {
}
