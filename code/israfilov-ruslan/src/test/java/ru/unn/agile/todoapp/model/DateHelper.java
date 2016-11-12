package ru.unn.agile.todoapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateHelper {
    private DateHelper() { }

    public static Date makeDate(final String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }
}
