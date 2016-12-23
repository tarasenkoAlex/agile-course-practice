package ru.unn.agile.Fraction.infrastructure;

import ru.unn.agile.Fraction.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FractionLogger implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter bufWriter;
    private final String fileName;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpledf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpledf.format(calendar.getTime());
    }

    public FractionLogger(final String filename) throws IOException {
        this.fileName = filename;
        BufferedWriter logWriter = null;
        logWriter = new BufferedWriter(new FileWriter(filename));
        bufWriter = logWriter;
    }

    @Override
    public void toLog(final String s) throws IOException {
            bufWriter.write(now() + " > " + s);
            bufWriter.newLine();
            bufWriter.flush();
    }

    @Override
    public List<String> getLog() throws IOException {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        return log;
    }
}
