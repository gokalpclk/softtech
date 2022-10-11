package com.baydijital.softtech.app.crd.entity;

import com.baydijital.softtech.app.crd.enums.CrdCreditCardActivityType;
import com.baydijital.softtech.app.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 8/3/22
 */
@Entity
@Table(name = "CRD_CREDIT_CARD_ACTIVITY")
@Getter
@Setter
public class CrdCreditCardActivity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "CrdCreditCardActivity", sequenceName = "CRD_CREDIT_CARD_ACTIVTY_ID_SEQ")
    @GeneratedValue(generator = "CrdCreditCardActivity")
    private Long id;

    @Column(name = "ID_CRD_CREDIT_CARD", nullable = false)
    private Long crdCreditCardId;

    @Column(name = "AMOUNT", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @Column(name = "CARD_ACTIVITY_TYPE", length = 30)
    @Enumerated(EnumType.STRING)
    private CrdCreditCardActivityType crdCreditCardActivityType;

}
