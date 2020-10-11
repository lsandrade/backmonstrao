package com.backmonstrao.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;

@Service
public class GeneratorService {

    public long generateData(int year, int month) {
        Random random = new Random(year + month);
        long start = getDateInMiliseconds(year, month, 1, 0, 0, 0, 0);
        long end = getDateInMiliseconds(year, month, 28, 23, 59, 59, 999);

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
}
