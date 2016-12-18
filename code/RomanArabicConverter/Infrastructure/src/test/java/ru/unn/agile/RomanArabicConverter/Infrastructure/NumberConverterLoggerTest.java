package ru.unn.agile.RomanArabicConverter.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import static org.junit.Assert.*;

public class NumberConverterLoggerTest {
    private static final String FILENAME = "./RomanArabicConverterLog.log";
    private NumberConverterLogger logger;

    @Before
    public void setUp() {
        logger = new NumberConverterLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
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
    public void canWriteLogMessage() {
        String testMessage = "Test message";
        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*Test message.*"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        logger.log(messages[0]);
        logger.log(messages[1]);

        List<String> logs = logger.getLog();

        assertTrue(logs.get(0).matches(".*Test message 1.*"));
        assertTrue(logs.get(1).matches(".*Test message 2.*"));
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}

