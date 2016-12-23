package ru.unn.agile.MortgageCalculator.infrastructure;

import ru.unn.agile.MortgageCalculator.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TextLogger implements ILogger {
    private static final String DATE_STRUCTURE = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter logsWriter;
    private final String fileName;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDateFormat = new SimpleDateFormat(DATE_STRUCTURE, Locale.ENGLISH);
        return currentDateFormat.format(calendar.getTime());
    }

    public TextLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logsWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            logsWriter = new BufferedWriter(fileWriter);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        this.logsWriter = logsWriter;
    }

    @Override
    public void log(final String message) {
        try {
            logsWriter.write(now() + " > " + message);
            logsWriter.newLine();
            logsWriter.flush();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public List<String> getLogList() {
        BufferedReader reader;
        ArrayList<String> logList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                logList.add(line);
                line = reader.readLine();
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        return logList;
    }

}
