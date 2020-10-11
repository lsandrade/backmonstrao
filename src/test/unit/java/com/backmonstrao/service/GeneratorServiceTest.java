package com.backmonstrao.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneratorServiceTest {

    public static final int VALID_YEAR = 2020;

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void mustGenerateDataBetweenFirstAndLastDay(int month) {

        GeneratorService generatorService = new GeneratorService();

        long start = getDateInMiliseconds(VALID_YEAR, month, 1, 0, 0, 0, 0);
        long end = getDateInMiliseconds(VALID_YEAR, month, 28, 23, 59, 59, 999);

        long dateGenerated = generatorService.generateData(VALID_YEAR, month);

        assertTrue(dateGenerated >= start);
        assertTrue(dateGenerated <= end);
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