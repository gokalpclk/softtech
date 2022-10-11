package com.baydijital.softtech.app.crd.enums;

import com.baydijital.softtech.app.gen.enums.BaseErrorMessage;

/**
 * @author Gokalp on 7/19/22
 */
public enum CrdErrorMessage implements BaseErrorMessage {
    INVALID_CREDIT_CARD("Invalid credit card!"),
    INSUFFICIENT_CREDIT_CARD_LIMIT("Insufficient card limit"),
    CREDIT_CARD_EXPIRED("Credit card expired!")
    ;

    String message;
    CrdErrorMessage(String message) {
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
