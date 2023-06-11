package com.example.incoming.dto.response;

import com.example.incoming.entity.Invest;
import lombok.Data;

@Data
public class InvestDetalizationRespDto {
    public InvestDetalizationRespDto(Invest invest) {
        this.id = String.valueOf(invest.getId());
        this.name = invest.getNameInvest();
        this.nameAccount = invest.getAccount().getNameAccount();
        this.sum = String.valueOf(invest.getSumInvest());
        this.dateReturn = invest.getDateReturn();
    }

    private String id;
    private String name;
    private String nameAccount;
    private String sum;
    private String dateReturn;
}
