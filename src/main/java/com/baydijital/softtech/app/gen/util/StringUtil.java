package com.baydijital.softtech.app.gen.util;

import com.baydijital.softtech.app.gen.enums.GenErrorMessage;
import com.baydijital.softtech.app.gen.exceptions.GenBusinessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

/**
 * @author Gokalp on 9/5/22
 */
public class StringUtil {
    public static Long getRandomNumber(int charCount) {
        Long randomLong = null;
        String randomNumeric = getRandomNumberAsString(charCount);
        if (StringUtils.hasText(randomNumeric)) {
            randomLong = Long.parseLong(randomNumeric);
        }
        return randomLong;
    }

    public static String getRandomNumberAsString(int charCount) {
        validateCharCount(charCount);
        String randomNumeric;
        do {
            randomNumeric = RandomStringUtils.randomNumeric(charCount);
        } while (randomNumeric.startsWith("0"));

        return randomNumeric;
    }

    public static String getRandomString(int charCount) {
        validateCharCount(charCount);
        String randomAlphabetic = RandomStringUtils.randomAlphabetic(charCount);
        return randomAlphabetic;
    }

    private static void validateCharCount(int charCount) {
        if (charCount < 0) {
            throw new GenBusinessException(GenErrorMessage.VALUE_CANNOT_BE_NEGATIVE);
        }
    }
}
