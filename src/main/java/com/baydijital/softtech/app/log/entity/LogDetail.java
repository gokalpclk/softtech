package com.baydijital.softtech.app.log.entity;

import com.baydijital.softtech.app.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Gokalp on 9/20/22
 */
@Entity
@Table(name = "LOG_DETAIL")
@Getter
@Setter
public class LogDetail extends BaseEntity {

    @GeneratedValue(generator = "LogDetail")
    @SequenceGenerator(name = "LogDetail", sequenceName = "LOG_DETAIL_ID_SEQ")
    @Id
    private Long id;

    private String message;
    private String details;
    private Date dateTime;

}
