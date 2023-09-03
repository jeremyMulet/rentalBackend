package com.chatop.rentalbackend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jérémy MULET on 24/08/2023.
 */
public class DateUtils {

    private static final String DEFAULT_DATE_PATTERN = "yyyy/MM/dd";

    private static DateTimeFormatter getDefaultFormatter() {
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(getDefaultFormatter());
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(customFormatter);
    }

}
