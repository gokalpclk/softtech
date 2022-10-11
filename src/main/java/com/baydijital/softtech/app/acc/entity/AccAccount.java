package com.baydijital.softtech.app.acc.entity;

import com.baydijital.softtech.app.acc.enums.AccAccountType;
import com.baydijital.softtech.app.acc.enums.AccCurrencyType;
import com.baydijital.softtech.app.gen.entity.BaseEntity;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 7/22/22
 */
@Entity
@Getter
@Setter
@Table(name = "ACC_ACCOUNT")
public class AccAccount extends BaseEntity {

    @Id
    @SequenceGenerator(name = "AccAccount", sequenceName = "ACC_ACCOUNT_ID_SEQ")
    @GeneratedValue(generator = "AccAccount")
    private Long id;

    @Column(name = "ID_CUS_CUSTOMER")
    private Long cusCustomerId;

    @Column(name = "IBAN_NO", length = 30)
    private String ibanNo;

    @Column(name = "CURRENT_BALANCE", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY_TYPE", length = 30)
    private AccCurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE", length = 30)
    private AccAccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_TYPE", length = 30)
    private GenStatusType statusType;

    @Column(name = "CANCEL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;



}
