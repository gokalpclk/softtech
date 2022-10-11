package com.baydijital.softtech.app.acc.dto;

import com.baydijital.softtech.app.acc.enums.AccMoneyTransferType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 7/23/22
 */
@Data
public class AccMoneyTransferSaveRequestDto {


    private Long accountIdFrom;

    private Long accountIdTo;

    private BigDecimal amount;

    private String description;


    private AccMoneyTransferType transferType;

}
