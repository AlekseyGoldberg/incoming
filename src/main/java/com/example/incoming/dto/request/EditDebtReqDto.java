package com.example.incoming.dto.request;

import lombok.Data;

@Data
public class EditDebtReqDto {
    private String id;
    private String nameDebt;
    private String sum_debt;
    private String account;
    private String dateReturn;
}
