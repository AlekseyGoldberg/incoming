package com.example.incoming.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OkRespDto {
    public OkRespDto() {
        this.message = "ok";
    }

    private String message;
}
