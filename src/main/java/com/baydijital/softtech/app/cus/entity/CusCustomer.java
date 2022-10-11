package com.baydijital.softtech.app.cus.entity;

import com.baydijital.softtech.app.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Gokalp on 7/19/22
 */
@Entity
@Data
@Table(name = "CUS_CUSTOMER")
public class CusCustomer  extends BaseEntity {

    @Id
    @SequenceGenerator(name = "CusCustomer", sequenceName = "CUS_CUSTOMER_ID_SEQ")
    @GeneratedValue(generator = "CusCustomer")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Column(name = "IDENTITY_NO")
    private Long identityNo;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
