package com.example.incoming.dto.response;

import lombok.Data;

import java.util.List;

@Data

public class AccountsRespDto {
    public AccountsRespDto(List<AccountRespDto> result, Double totalAmount) {
        this.result = result;
        this.totalAmount = String.valueOf(totalAmount);
    }

    private List<AccountRespDto> result;
    private String totalAmount;
}
