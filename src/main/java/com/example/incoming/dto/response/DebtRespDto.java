package com.example.incoming.dto.response;

import com.example.incoming.entity.Debt;
import lombok.Data;

@Data
public class DebtRespDto {
    public DebtRespDto(Debt debt) {
        this.id = String.valueOf(debt.getId());
        this.message = debt.getNameDebt();
        this.amount = String.valueOf(debt.getSum_debt());
    }

    private String id;
    private String message;
    private String amount;
}
