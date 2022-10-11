package com.baydijital.softtech.app.gen.util;

import com.baydijital.softtech.app.gen.enums.GenErrorMessage;
import com.baydijital.softtech.app.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gokalp on 9/6/22
 */
class StringUtilTest {


    @Test
    void shouldGetRandomNumber() {
        int charCount = 5;
        Long randomNumber = StringUtil.getRandomNumber(charCount);
        Assertions.assertEquals(charCount, String.valueOf(randomNumber).length());
    }
    @Test
    void shouldNotGetRandomNumberWhenCharCountIsZero() {
        int charCount = 0;
        Long randomNumber = StringUtil.getRandomNumber(charCount);
        Assertions.assertNull(randomNumber);
    }



    @Test
    void shouldNotGetRandomNumberWhenCharCountTooLong() {
        int charCount = 50;
        Assertions.assertThrows(NumberFormatException.class, () -> StringUtil.getRandomNumber(charCount));
    }


    @Test
    void shouldGetRandomNumberAsString() {
        int charCount = 5;
        String randomNumberAsString = StringUtil.getRandomNumberAsString(charCount);
        Assertions.assertEquals(charCount, randomNumberAsString.length());
    }

    @Test
    void shouldNotGetRandomNumberAsStringWhenCharCountIsNegative() {
        int charCount = -1;
        GenBusinessException genBusinessException = Assertions.assertThrows(GenBusinessException.class, () -> StringUtil.getRandomNumberAsString(charCount));
        Assertions.assertEquals(GenErrorMessage.VALUE_CANNOT_BE_NEGATIVE, genBusinessException.getBaseErrorMessage());
    }

    @Test
    void shouldGetRandomNumberAsWhenCharCountIsZero() {
        int charCount = 0;
        String randomNumberAsString = StringUtil.getRandomNumberAsString(charCount);
        Assertions.assertEquals("", randomNumberAsString);
        Assertions.assertEquals(0, randomNumberAsString.length());
    }


    @Test
    void shouldGetRandomString() {
        int charCount = 5;
        String randomString = StringUtil.getRandomString(charCount);
        Assertions.assertEquals(charCount, randomString.length());
    }
}