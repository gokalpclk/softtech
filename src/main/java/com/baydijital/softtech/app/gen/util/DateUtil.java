package com.baydijital.softtech.app.gen.util;

import com.baydijital.softtech.app.gen.enums.GenErrorMessage;
import com.baydijital.softtech.app.gen.exceptions.GenBusinessException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * @author Gokalp on 8/4/22
 */
public class DateUtil {
    public static Date convertToDate(LocalDate localDate) {
        validateLocalDate(localDate);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private static void validateLocalDate(LocalDate localDate) {
        if (localDate == null) {
            throw new GenBusinessException(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED);
        }
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDate(Date date) {
        validateDate(date);
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static void validateDate(Date date) {
        if (date == null) {
            throw new GenBusinessException(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED);
        }
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        validateDate(date);
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
