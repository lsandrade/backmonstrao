package com.backmonstrao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorServiceTest {

    public static final int VALID_YEAR = 2020;
    public static final int MIN_VALUE = -9999999;
    public static final int MAX_VALUE = 9999999;

    GeneratorService generatorService;

    @BeforeEach
    void setUp() {
        generatorService = new GeneratorService();
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void mustGenerateDataBetweenFirstAndLastDay(int month) {

        long start = getDateInMiliseconds(VALID_YEAR, month, 1, 0, 0, 0, 0);
        long end = getDateInMiliseconds(VALID_YEAR, month, 28, 23, 59, 59, 999);

        long dateGenerated = generatorService.generateData(VALID_YEAR, month, 1);

        assertTrue(dateGenerated >= start);
        assertTrue(dateGenerated <= end);
    }

    @Test
    void mustGenerateValueBetweenRange() {
        Integer value1 = generatorService.generateValue( VALID_YEAR + 1);
        Integer value2 = generatorService.generateValue( MAX_VALUE);

        assertTrue(value1 >= MIN_VALUE);
        assertTrue(value1 <= MAX_VALUE);

        assertNotEquals(value1, value2);

        assertTrue(value2 >= MIN_VALUE);
        assertTrue(value2 <= MAX_VALUE);
    }

    private long getDateInMiliseconds(int year, int month, int day, int hour, int minute, int second, int mili) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, mili);

        return calendar.getTimeInMillis();
    }


}