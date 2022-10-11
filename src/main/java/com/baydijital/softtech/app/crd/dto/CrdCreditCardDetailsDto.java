package com.baydijital.softtech.app.crd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Gokalp on 8/8/22
 */
@Data
@RequiredArgsConstructor
public class CrdCreditCardDetailsDto {
    private final String customerName;
    private final String customerSurname;
    private final Long cardNo;
    private final Date expireDate;
    private final BigDecimal currentDebt;
    private final BigDecimal minimumPaymentAmount;
    private final Date cutOfDate;
    private final Date dueDate;
    private List<CrdCreditCardActivityDto> crdCreditCardActivityDtoList;

}
