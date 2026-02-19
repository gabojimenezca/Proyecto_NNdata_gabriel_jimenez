package com.example.accountservice.dto;

import com.example.accountservice.entity.MovementType;
import lombok.Data;

import java.math.BigDecimal;

@Data

public class MovementRequest {

    private Long accountId;

    private MovementType type;

    private BigDecimal value;
}
