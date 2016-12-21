package ru.unn.agile.RomanArabicConverter.Infrastructure;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import ru.unn.agile.RomanArabicConverter.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class NumberConverterLogger implements ILogger {

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter logWriter;
    private final String filename;
    private final BooleanProperty addedNewLogProperty = new SimpleBooleanProperty(false);


    private static String dateTimeNow() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return simpleDateFormat.format(cal.getTime());
    }

    public NumberConverterLogger(final String filename) {
        this.filename = filename;

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logWriter = bufferedWriter;
    }

    @Override
    public void log(final String s) {
        addedNewLogProperty.set(false);
        try {
            logWriter.write(dateTimeNow() + " > " + s);
            logWriter.newLine();
            logWriter.flush();
            addedNewLogProperty.set(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> allLogs = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();

            while (line != null) {
                allLogs.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return allLogs;
    }


    @Override
    public void setListener(final ChangeListener<Boolean> listener) {
        addedNewLogProperty.addListener(listener);
    }

}
