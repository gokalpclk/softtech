package com.baydijital.softtech.app.acc.dto;

import com.baydijital.softtech.app.acc.enums.AccAccountActivityType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Gokalp on 9/10/22
 */
@Data
@Builder
public class AccMoneyActivityDto {
    private Long accAccountId;
    private BigDecimal amount;
    private AccAccountActivityType accAccountActivityType;
}
