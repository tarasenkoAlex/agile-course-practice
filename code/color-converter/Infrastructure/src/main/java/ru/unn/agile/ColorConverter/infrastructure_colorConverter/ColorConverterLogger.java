package ru.unn.agile.ColorConverter.infrastructure_colorConverter;

import ru.unn.agile.ColorConverter.viewmodel.IColorConverterLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ColorConverterLogger implements IColorConverterLogger {
    private static final String CURRENT_DATE = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter bufWriter;
    private final String filename;

    private static String currentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CURRENT_DATE, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public ColorConverterLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logBufWriter = null;
        try {
            logBufWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufWriter = logBufWriter;
    }

    @Override
    public void log(final String s) {
        try {
            bufWriter.write(currentDate() + " > " + s);
            bufWriter.newLine();
            bufWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();

            while (line != null) {
                log.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

}
