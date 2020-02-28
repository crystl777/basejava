package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

<<<<<<< HEAD
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate dateFormatter(String date) {
        if (date == null) {
            return NOW;
        }

        return LocalDate.parse(date, formatter);
    }

    public static String dateToString(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(formatter);
=======
    public static LocalDate dateFormatter (String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
>>>>>>> parent of a887a03... HW16.4 :
    }
}
