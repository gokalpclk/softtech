package com.baydijital.softtech.app.gen.exceptions;

import com.baydijital.softtech.app.cus.enums.CusErrorMessage;
import com.baydijital.softtech.app.gen.enums.BaseErrorMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gokalp on 7/19/22
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends GenBusinessException{
    public ItemNotFoundException(BaseErrorMessage message) {
        super(message);
    }
}
