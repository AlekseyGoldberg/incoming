package com.example.incoming.dto.response;

import com.querydsl.core.Tuple;
import lombok.Data;

@Data
public class AccountRespDto {
    public AccountRespDto(Tuple tuple) {
        Object[] objects = tuple.toArray();
        this.id = String.valueOf(objects[0]);
        this.message = String.valueOf(objects[1]);
        this.amount = String.valueOf(objects[2]);
    }

    private String id;
    private String message;
    private String amount;
}
