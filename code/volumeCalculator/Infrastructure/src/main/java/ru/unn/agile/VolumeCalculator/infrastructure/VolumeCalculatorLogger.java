package ru.unn.agile.VolumeCalculator.infrastructure;

import ru.unn.agile.VolumeCalculator.viewModel.IVolumeCalculatorLogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
/**
 * Created by Elena on 12/18/2016.
 */
public class VolumeCalculatorLogger implements IVolumeCalculatorLogger {
    private static final String CURRENT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter buffWriter;
    private final String fileName;

    public VolumeCalculatorLogger(final String fileName) {
        this.fileName = fileName;
        FileWriter fWriter = null;
        try {
            fWriter = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffWriter = new BufferedWriter(fWriter);
    }

    private static String currentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(CURRENT_DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    @Override
    public void addLog(final String s) {
        try {
            buffWriter.write("[" + currentDate() + "] : " + s);
            buffWriter.newLine();
            buffWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader buffReader;
        List<String> logMessage = new ArrayList<String>();
        try {
            buffReader = new BufferedReader(new FileReader(fileName));
            String line = buffReader.readLine();
            while (line != null) {
                logMessage.add(line);
                line = buffReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return logMessage;
    }
}
