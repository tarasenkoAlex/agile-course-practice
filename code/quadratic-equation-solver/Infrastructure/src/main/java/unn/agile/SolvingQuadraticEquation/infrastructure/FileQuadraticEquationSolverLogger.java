package unn.agile.SolvingQuadraticEquation.infrastructure;

import ru.unn.agile.SolvingQuadraticEquation.viewmodel.IQuadraticEquationSolverLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FileQuadraticEquationSolverLogger implements IQuadraticEquationSolverLogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter fileWriter;
    private final String fileName;

    private static String getNowTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public FileQuadraticEquationSolverLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileWriter = logWriter;
    }

    @Override
    public void makeLog(final String s) {
        try {
            fileWriter.write("[" + getNowTime() + "] > " + s);
            fileWriter.newLine();
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getLog() {
        BufferedReader fileBufferedReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            fileBufferedReader = new BufferedReader(new FileReader(fileName));
            String line = fileBufferedReader.readLine();

            while (line != null) {
                log.add(line);
                line = fileBufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

}
