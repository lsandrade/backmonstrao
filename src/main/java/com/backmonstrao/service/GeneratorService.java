package com.backmonstrao.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;

@Service
public class GeneratorService {

    public static final int MIN_VALUE = -9999999;
    public static final int MAX_VALUE = 9999999;

    public long generateData(int year, int month) {
        long start = getDateInMiliseconds(year, month, 1, 0, 0, 0, 0);
        long end = getDateInMiliseconds(year, month, 28, 23, 59, 59, 999);

        return getRandomLong(year + month, start, end);
    }

    private long getRandomLong(int seed, long start, long end) {
        Random random = new Random(seed);
        return start + Math.abs(random.nextLong()) % (end - start);
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

    public Integer generateValue(int seed) {
        return Math.toIntExact(getRandomLong(seed, MIN_VALUE, MAX_VALUE));
    }
}
