package ru.unn.agile.Credit.infrastructure;

import ru.unn.agile.Credit.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String filename;

    BufferedWriter getWriter() {
        return writer;
    }
    private static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.writer = writer;
    }

    @Override
    public void log(final String string) {
        try {
            writer.write(now() + " > " + string);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    List<String> getLog(final boolean isTesting) {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<>();

        try {
            if (isTesting) {
                reader = null;
            } else {
                reader = new BufferedReader(new FileReader(filename));
            }
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return log;
    }

    @Override
    public List<String> getLog() {
        return getLog(false);
    }

}
