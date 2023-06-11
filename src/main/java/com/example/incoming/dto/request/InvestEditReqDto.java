package com.example.incoming.dto.request;

import lombok.Data;

@Data
public class InvestEditReqDto {
    private String id;
    private String name;
    private String sum;
    private String dateReturn;
    private String accountName;
}
