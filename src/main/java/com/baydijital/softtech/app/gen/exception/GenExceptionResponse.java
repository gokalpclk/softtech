package com.baydijital.softtech.app.gen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author Gokalp on 7/19/22
 */
@Data
@AllArgsConstructor
public class GenExceptionResponse {
    private Date errorDate;
    private String message;
    private String description;
}
