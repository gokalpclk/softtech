package com.baydijital.softtech.app.acc.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Gokalp on 7/23/22
 */
@Data
public class AccMoneyActivityRequestDto {
    private Long accAccountId;
    private BigDecimal amount;

}
