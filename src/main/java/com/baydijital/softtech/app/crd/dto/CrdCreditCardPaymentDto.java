package com.baydijital.softtech.app.crd.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Gokalp on 8/8/22
 */
@Data
public class CrdCreditCardPaymentDto {

    private Long crdCreditCardId;
    private BigDecimal amount;

}
