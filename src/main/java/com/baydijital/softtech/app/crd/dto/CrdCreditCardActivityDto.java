package com.baydijital.softtech.app.crd.dto;

import com.baydijital.softtech.app.crd.enums.CrdCreditCardActivityType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 8/8/22
 */
@Data
public class CrdCreditCardActivityDto {

    private Long crdCreditCardId;
    private BigDecimal amount;
    private Date transactionDate;
    private String description;
    private CrdCreditCardActivityType crdCreditCardActivityType;
}
