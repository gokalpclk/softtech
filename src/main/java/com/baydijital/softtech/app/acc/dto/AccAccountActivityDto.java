package com.baydijital.softtech.app.acc.dto;

import com.baydijital.softtech.app.acc.enums.AccAccountActivityType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 7/26/22
 */
@Data
public class  AccAccountActivityDto {


    private Long accAccountId;

    private BigDecimal amount;

    private Date transactionDate;

    private BigDecimal currentBalance;

    private AccAccountActivityType activityType;
}
