package com.baydijital.softtech.app.crd.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 8/8/22
 */
@Data
public class CrdCreditCardSpendDto {
    private Long cardNo;
    private Long cvvNo;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expireDate;

    private BigDecimal amount;
    private String description;
}