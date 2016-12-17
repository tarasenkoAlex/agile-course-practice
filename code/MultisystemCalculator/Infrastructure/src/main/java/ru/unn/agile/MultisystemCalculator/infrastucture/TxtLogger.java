package ru.unn.agile.MultisystemCalculator.infrastucture;

import ru.unn.agile.MultisystemCalculator.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String filename;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public TxtLogger(final String filename) throws IOException {
        this.filename = filename;

        BufferedWriter logWriter = null;
        logWriter = new BufferedWriter(new FileWriter(filename));
        writer = logWriter;
    }

    @Override
    public void log(final String s) throws IOException {
        writer.write(now() + " > " + s);
        writer.newLine();
        writer.flush();
    }

    @Override
    public List<String> getLog() throws IOException {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();

        while (line != null) {
            log.add(line);
            line = reader.readLine();
        }
        return log;
    }
}
