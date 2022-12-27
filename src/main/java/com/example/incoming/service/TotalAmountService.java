package com.example.incoming.service;

import com.example.incoming.entity.TotalAmount;
import com.example.incoming.repository.Impl.TotalAmountRepository;
import org.springframework.stereotype.Service;

@Service
public class TotalAmountService {
    private final TotalAmountRepository totalAmountRepository;

    public TotalAmountService(TotalAmountRepository totalAmountRepository) {
        this.totalAmountRepository = totalAmountRepository;
    }

//    public TotalAmount updateTotalIncome(int userId){
//        return totalAmountRepository.updateTotalIncome(userId);
//    }
}
