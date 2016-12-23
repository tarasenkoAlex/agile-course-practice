package ru.unn.agile.MyDeque.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

public class TxtLoggerTests {
    private static final String FILE = "./TestLab3.log";
    private TxtLogger txtLogger;

    @Before
    public void init() {
        txtLogger = new TxtLogger(FILE);
    }

    @Test
    public void createLogWithNameOfFile() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILE));
        } catch (FileNotFoundException e) {
            fail("File '" + FILE + "' isn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, RgxMatcher.matchPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteDifferentLogMessage() {
        String[] mess = {"Test one", "Test two", "Test three"};

        txtLogger.log(mess[0]);
        txtLogger.log(mess[1]);
        txtLogger.log(mess[2]);

        List<String> actualMess = txtLogger.getLog();
        for (int i = 0; i < actualMess.size(); i++) {
            assertThat(actualMess.get(i),
                    RgxMatcher.matchPattern(".*" + mess[i] + "$"));
        }
    }

    @Test
    public void logContainsDateAndTime() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message,
                RgxMatcher.matchPattern("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
