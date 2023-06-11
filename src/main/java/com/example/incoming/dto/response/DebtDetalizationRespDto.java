package com.example.incoming.dto.response;


import com.example.incoming.entity.Debt;
import lombok.Data;

@Data
public class DebtDetalizationRespDto {
    public DebtDetalizationRespDto(Debt debt) {
        this.id = String.valueOf(debt.getId());
        this.name = debt.getNameDebt();
        this.nameAccount = debt.getAccount().getNameAccount();
        this.sum = String.valueOf(debt.getSum_debt());
        this.dateReturn = debt.getDateReturn();
    }

    private String id;
    private String name;
    private String nameAccount;
    private String sum;
    private String dateReturn;
}
