package ru.unn.agile.todoapp.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DateHelper {
    private DateHelper() { }

    public static LocalDate makeDate(final String date) throws ParseException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, df);
    }
}
