package com.example.incoming.dto.request;

import lombok.Data;

@Data
public class DebtReqDto {
    private String nameDebt;
    private String sum_debt;
    private String account;
    private String dateReturn;
}
