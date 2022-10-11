package com.baydijital.softtech.app.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author Gokalp on 9/19/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogMessage implements Serializable {
    private Long id;
    private String message;
    private String description;
    private Date dateTime;
}
