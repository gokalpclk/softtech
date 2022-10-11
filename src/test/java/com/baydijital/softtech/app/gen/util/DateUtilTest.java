package com.baydijital.softtech.app.gen.util;

import com.baydijital.softtech.app.gen.enums.GenErrorMessage;
import com.baydijital.softtech.app.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gokalp on 9/5/22
 */
class DateUtilTest {
    private static SimpleDateFormat formatterDate;
    private static SimpleDateFormat formatterDateTime;
    @BeforeAll
    public static void setUp(){
        formatterDate = new SimpleDateFormat("dd-MM-yyyy");
        formatterDateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    }

    @Test
    void shouldConvertToLocalDate() throws ParseException {
        Date date = formatterDate.parse("05-10-1991");

        LocalDate localDate = DateUtil.convertToLocalDate(date);

        Assertions.assertEquals(5, localDate.getDayOfMonth());
        Assertions.assertEquals(10, localDate.getMonthValue());
        Assertions.assertEquals(1991, localDate.getYear());
    }

    @Test
    void shouldNotConvertToLocalDateWhenParameterIsNull() {
        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> DateUtil.convertToLocalDate(null));
        Assertions.assertEquals(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED, genBusinessException.getBaseErrorMessage());
    }

    @Test
    void shouldConvertToLocalDateWhen29Feb() throws ParseException {

        Date date = formatterDate.parse("29-02-2016");

        LocalDate localDate = DateUtil.convertToLocalDate(date);

        Assertions.assertEquals(29, localDate.getDayOfMonth());
        Assertions.assertEquals(2, localDate.getMonthValue());
        Assertions.assertEquals(2016, localDate.getYear());
    }

    @Test
    void shouldConvertToLocalDateWhen01Jan() throws ParseException {

        Date date = formatterDate.parse("01-01-2016");

        LocalDate localDate = DateUtil.convertToLocalDate(date);

        Assertions.assertEquals(1, localDate.getDayOfMonth());
        Assertions.assertEquals(1, localDate.getMonthValue());
        Assertions.assertEquals(2016, localDate.getYear());
    }

    @Test
    void shouldConvertToLocalDateWhen31Dec() throws ParseException {

        Date date = formatterDate.parse("31-12-2016");

        LocalDate localDate = DateUtil.convertToLocalDate(date);

        Assertions.assertEquals(31, localDate.getDayOfMonth());
        Assertions.assertEquals(12, localDate.getMonthValue());
        Assertions.assertEquals(2016, localDate.getYear());
    }


    @Test
    void shouldConvertToLocalDateTime() throws ParseException {
        Date date = formatterDateTime.parse("02-10-1997 10:11:12");

        LocalDateTime localDateTime = DateUtil.convertToLocalDateTime(date);

        Assertions.assertEquals(02, localDateTime.getDayOfMonth());
        Assertions.assertEquals(10, localDateTime.getMonthValue());
        Assertions.assertEquals(1997, localDateTime.getYear());
        Assertions.assertEquals(10, localDateTime.getHour());
        Assertions.assertEquals(11, localDateTime.getMinute());
        Assertions.assertEquals(12, localDateTime.getSecond());
    }

    @Test
    void shouldNotConvertToLocalDateTimeWhenParameterIsNull(){

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> DateUtil.convertToLocalDateTime(null));
        Assertions.assertEquals(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED, genBusinessException.getBaseErrorMessage());

    }

    @Test
    void shouldConvertToLocalDateTimeWhenTimeIs000000() throws ParseException {
        Date date = formatterDateTime.parse("02-10-1997 00:00:00");

        LocalDateTime localDateTime = DateUtil.convertToLocalDateTime(date);

        Assertions.assertEquals(02, localDateTime.getDayOfMonth());
        Assertions.assertEquals(10, localDateTime.getMonthValue());
        Assertions.assertEquals(1997, localDateTime.getYear());
        Assertions.assertEquals(0, localDateTime.getHour());
        Assertions.assertEquals(0, localDateTime.getMinute());
        Assertions.assertEquals(0, localDateTime.getSecond());
    }

    @Test
    void shouldConvertToLocalDateTimeWhenTimeIs235959() throws ParseException {
        Date date = formatterDateTime.parse("02-10-1997 23:59:59");

        LocalDateTime localDateTime = DateUtil.convertToLocalDateTime(date);

        Assertions.assertEquals(2, localDateTime.getDayOfMonth());
        Assertions.assertEquals(10, localDateTime.getMonthValue());
        Assertions.assertEquals(1997, localDateTime.getYear());
        Assertions.assertEquals(23, localDateTime.getHour());
        Assertions.assertEquals(59, localDateTime.getMinute());
        Assertions.assertEquals(59, localDateTime.getSecond());
        localDateTime=localDateTime.plusSeconds(1);
        Assertions.assertEquals(3, localDateTime.getDayOfMonth());
        Assertions.assertEquals(10, localDateTime.getMonthValue());
        Assertions.assertEquals(1997, localDateTime.getYear());
        Assertions.assertEquals(0, localDateTime.getHour());
        Assertions.assertEquals(0, localDateTime.getMinute());
        Assertions.assertEquals(0, localDateTime.getSecond());
    }

    @Test
    void shouldConvertToDate(){
        LocalDate localDate = LocalDate.of(1991, 10, 5);

        Date date = DateUtil.convertToDate(localDate);
        String format = formatterDate.format(date);
        assertEquals("05-10-1991", format);
    }

    @Test
    void shouldLocalDateTimeConvertToDate() throws ParseException {
        Date date = formatterDateTime.parse("02-10-1997 23:59:59");

        LocalDateTime localDateTime = DateUtil.convertToLocalDateTime(date);
        Date convertToDate = DateUtil.convertToDate(localDateTime);
        String format = formatterDate.format(convertToDate);

        assertEquals("02-10-1997", format);
    }

    @Test
    void shouldNotConvertWhenParameterIsNull() {
        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> DateUtil.convertToDate((LocalDate) null));
        assertEquals(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED, genBusinessException.getBaseErrorMessage());


    }

}