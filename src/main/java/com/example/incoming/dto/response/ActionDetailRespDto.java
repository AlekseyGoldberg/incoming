package com.example.incoming.dto.response;

import lombok.Data;

@Data
public class ActionDetailRespDto {
    private String id;
    private String date;
    private String account;
    private String sum;
    private String comment;
    private String typeOperation;
}
