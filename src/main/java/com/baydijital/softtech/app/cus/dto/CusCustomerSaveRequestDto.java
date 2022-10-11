package com.baydijital.softtech.app.cus.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Gokalp on 7/19/22
 */
@Data
@Builder
public class CusCustomerSaveRequestDto {
    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
