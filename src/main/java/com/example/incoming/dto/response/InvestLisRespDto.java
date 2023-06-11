package com.example.incoming.dto.response;

import com.example.incoming.entity.Invest;
import lombok.Data;

@Data

public class InvestLisRespDto {
    public InvestLisRespDto(Invest invest) {
        this.id = String.valueOf(invest.getId());
        this.message = invest.getNameInvest();
        this.amount = String.valueOf(invest.getSumInvest());
    }

    private String id;
    private String message;
    private String amount;
}
