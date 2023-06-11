package com.example.incoming.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DebtsRespDto {
    private List<DebtRespDto> result;
    private String sumAmount;
}
