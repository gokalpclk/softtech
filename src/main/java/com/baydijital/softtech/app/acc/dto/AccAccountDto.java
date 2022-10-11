package com.baydijital.softtech.app.acc.dto;

import com.baydijital.softtech.app.acc.enums.AccAccountType;
import com.baydijital.softtech.app.acc.enums.AccCurrencyType;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 7/23/22
 */
@Data
public class AccAccountDto {

    private Long id;

    private Long cusCustomerId;

    private String ibanNo;

    private BigDecimal currentBalance;

    private AccCurrencyType currencyType;

    private AccAccountType accountType;

    private GenStatusType statusType;

    private Date cancelDate;
}
