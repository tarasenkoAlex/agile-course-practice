package ru.unn.agile.Fraction.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.Fraction.infrastructure.RegMatcher.matchesPattern;

public class FractionLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests.log";
    private FractionLogger fractionLogger;

    @Before
    public void setUp() throws IOException {
        fractionLogger = new FractionLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(fractionLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() throws IOException {
        String testMessage = "Test message";

        fractionLogger.toLog(testMessage);

        String message = fractionLogger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralFractionLogMessages() throws IOException {
        String[] fractionMessages = {"Test message #1", "Test message #2"};

        fractionLogger.toLog(fractionMessages[0]);
        fractionLogger.toLog(fractionMessages[1]);

        List<String> actualFractionMessages = fractionLogger.getLog();
        for (int i = 0; i < actualFractionMessages.size(); i++) {
            assertThat(actualFractionMessages.get(i),
                    matchesPattern(".*" + fractionMessages[i] + "$"));
        }
    }

    @Test
    public void doesLoggerContainDateAndTime() throws IOException {
        String testMessage = "Test message";

        fractionLogger.toLog(testMessage);

        String message = fractionLogger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
