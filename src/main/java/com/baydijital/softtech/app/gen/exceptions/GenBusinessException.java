package com.baydijital.softtech.app.gen.exceptions;

import com.baydijital.softtech.app.gen.enums.BaseErrorMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gokalp on 8/1/22
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@RequiredArgsConstructor
@Data
public class GenBusinessException extends RuntimeException {
    private final BaseErrorMessage baseErrorMessage;
}
