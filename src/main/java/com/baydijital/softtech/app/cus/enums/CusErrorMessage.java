package com.baydijital.softtech.app.cus.enums;

import com.baydijital.softtech.app.gen.enums.BaseErrorMessage;

/**
 * @author Gokalp on 7/19/22
 */
public enum CusErrorMessage implements BaseErrorMessage {
    CUSTOMER_ERROR_MESSAGE("Customer not found!"),
    ;

    String message;
    CusErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
