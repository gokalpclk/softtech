package com.baydijital.softtech.app.gen.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Gokalp on 8/1/22
 */
@MappedSuperclass
@Getter
@Setter
public abstract class  BaseEntity implements BaseModel, Cloneable, Serializable {

    private static final Long seralVersionUID = 1L;

    @Embedded
    private BaseAdditionalFields baseAdditionalFields;
}
