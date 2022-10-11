package com.baydijital.softtech.app.acc.dto;

import com.baydijital.softtech.app.acc.enums.AccAccountType;
import com.baydijital.softtech.app.acc.enums.AccCurrencyType;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @author Gokalp on 7/23/22
 */
@Data
public class AccAccountSaveRequestDto {

    private BigDecimal currentBalance;
    private AccCurrencyType currencyType;
    private AccAccountType accountType;
    private GenStatusType statusType;
}
