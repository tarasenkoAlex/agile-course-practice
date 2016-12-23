package ru.unn.agile.CurrencyConverter.infrastructure;

import ru.unn.agile.CurrencyConverter.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private final String filename;
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public TxtLogger(final String filename) throws IOException {
        this.filename = filename;
        BufferedWriter bufferedWriter = null;
        bufferedWriter = new BufferedWriter(new FileWriter(filename));
        writer = bufferedWriter;
    }

    @Override
    public void logIt(final String s) throws IOException {
        writer.write(now() + " > " + s);
        writer.newLine();
        writer.flush();
    }

    @Override
    public List<String> getLog() throws IOException {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<>();
        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null) {
            log.add(line);
            line = reader.readLine();
        }
        return log;
    }
}
