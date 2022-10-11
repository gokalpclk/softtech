package com.baydijital.softtech.app.crd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Gokalp on 8/8/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrdCreditCardDto {
    private Long cardNo;
    private Long cvvNo;
    private Date expireDate;
}
