package com.baydijital.softtech.app.crd.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 8/4/22
 */
@Data
public class CrdCreditCardSaveRequestDto {


    @NotNull
    private BigDecimal earning;

    private String cutoffDay;
}
