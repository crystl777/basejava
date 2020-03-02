package com.urise.webapp.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate dateFormatter(String date) {
        if (date == null || date.trim().length() == 0) {
            Date d = new Date();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = d.toInstant();
            return instant.atZone(defaultZoneId).toLocalDate();
        }
        return LocalDate.parse(date, formatter);
    }

    public static String dateToString(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(formatter);
    }
}
