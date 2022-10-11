package com.baydijital.softtech.app.acc.entity;

import com.baydijital.softtech.app.acc.enums.AccAccountActivityType;
import com.baydijital.softtech.app.gen.entity.BaseEntity;
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
@Table(name = "ACC_ACCOUNT_ACTIVITY")
public class AccAccountActivity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "AccAccountActivity", sequenceName = "ACC_ACCOUNT_ACTIVITY_ID_SEQ")
    @GeneratedValue(generator = "AccAccountActivity")
    private Long id;

    @Column(name = "ID_ACC_ACCOUNT")
    private Long accAccountId;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Column(name = "CURRENT_BALANCE", scale = 2, precision = 19)
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTIVITY_TYPE", length = 30)
    private AccAccountActivityType activityType;
}
