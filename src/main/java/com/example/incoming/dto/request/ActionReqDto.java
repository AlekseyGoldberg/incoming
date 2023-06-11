package com.example.incoming.dto.request;

import lombok.Data;

@Data
public class ActionReqDto {

    private String id;
    private String typeAction;
    private String date;
    private String account;
    private String amount;
    private String category;
    private String comment;
}
