package com.baydijital.softtech.app.crd.dto;

import com.baydijital.softtech.app.gen.enums.GenStatusType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 8/4/22
 */
@Data
public class CrdCreditCardResponseDto {
    private long id;

    private Long cusCustomerId;

    private Long cardNo;

    private Long cvvNo;

    private Date expireDate;

    private BigDecimal totalLimit;

    private BigDecimal availableCardLimit;

    private BigDecimal currentDebt;

    private BigDecimal minimumPaymentAmount;

    private Date cutoffDate;

    private Date dueDate;

    private GenStatusType statusType;

    private Date cancelDate;
}
