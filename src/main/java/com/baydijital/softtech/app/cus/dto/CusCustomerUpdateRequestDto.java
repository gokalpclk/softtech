package com.baydijital.softtech.app.cus.dto;

import lombok.Data;

/**
 * @author Gokalp on 7/19/22
 */
@Data
public class CusCustomerUpdateRequestDto {
    private Long id;
    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
