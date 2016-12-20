package ru.unn.agile.vector3d.infrastructure;

import ru.unn.agile.vector3d.viewmodel.AbstractLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

public class FileLogger implements AbstractLogger {
    private static final String LOG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter fileWritter;
    private final BufferedReader fileReader;
    private final String fileName;

    public FileLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.fileName));
            reader = new BufferedReader(new FileReader(this.fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileWritter = writer;
        fileReader = reader;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void putLog(final String message, final Object... args) {
        try {
            String str = MessageFormat.format(message, args);
            fileWritter.write(getTimestamp() + " > " + MessageFormat.format(message, args));
            fileWritter.newLine();
            fileWritter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Iterator<String> iterator() {
        return fileReader.lines().iterator();
    }

    private String getTimestamp() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(LOG_DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }
}
