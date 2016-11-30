package ru.unn.agile.PersonalFinance.ViewModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

final class GregorianCalendarHelper {
    public static LocalDate convertToLocalDate(final GregorianCalendar calendar) {
        return calendar.toZonedDateTime().toLocalDate();
    }

    public static GregorianCalendar convertFromLocalDate(final LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return GregorianCalendar.from(zonedDateTime);
    }

    public static GregorianCalendar now() {
        return convertFromLocalDate(LocalDate.now());
    }

    private GregorianCalendarHelper() {
    }
}
