package com.example.incoming.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InvestAllRespDto {
    private List<InvestLisRespDto> result;
    private double sumAmount;

}
