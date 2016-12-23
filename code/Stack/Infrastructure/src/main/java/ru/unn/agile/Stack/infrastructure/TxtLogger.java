package ru.unn.agile.Stack.infrastructure;

import ru.unn.agile.Stack.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;


public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter loggerWriter;
    private final String fileName;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.loggerWriter = writer;
    }

    @Override
    public void log(final String s) {
        try {
            loggerWriter.write(now() + " > " + s);
            loggerWriter.newLine();
            loggerWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLog() {
        String log = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                log += line;
                log += '\n';
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }
}
