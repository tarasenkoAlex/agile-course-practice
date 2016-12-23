package ru.unn.agile.Stack.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static junit.framework.TestCase.assertNotNull;


public class TxtLoggerTests {
    private static final String TEST_FILENAME = "./TxtLogger_Tests-lab3.log";
    private TxtLogger logger;

    @Before
    public void setUp() {
        logger = new TxtLogger(TEST_FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(TEST_FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + TEST_FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog();
        assertTrue(message.matches(".*" + testMessage + "$\n"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        logger.log(messages[0]);
        logger.log(messages[1]);

        String[] actualMessages = logger.getLog().split("\n");
        for (int i = 0; i < actualMessages.length; i++) {
            assertTrue(actualMessages[i].matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog();
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*\n"));
    }
}
