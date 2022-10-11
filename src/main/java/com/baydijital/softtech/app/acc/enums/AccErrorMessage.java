package com.baydijital.softtech.app.acc.enums;

import com.baydijital.softtech.app.gen.enums.BaseErrorMessage;

/**
 * @author Gokalp on 7/19/22
 */
public enum AccErrorMessage implements BaseErrorMessage {
    INSUFFICIENT_BALANCE("Insufficient balance!"),
    ;

    String message;
    AccErrorMessage(String message) {
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
