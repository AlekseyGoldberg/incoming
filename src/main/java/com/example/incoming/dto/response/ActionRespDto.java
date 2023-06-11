package com.example.incoming.dto.response;

import lombok.Data;

@Data
public class ActionRespDto {
    private String id;
    private String date;
    private String account;
    private String sum;
    private String typeOperation;
}
