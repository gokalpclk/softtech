package com.baydijital.softtech.app.sec.dto;

import lombok.Data;

/**
 * @author Gokalp on 9/4/22
 */
@Data
public class SecLoginRequestDto {
    private Long identityNo;
    private String password;
}
