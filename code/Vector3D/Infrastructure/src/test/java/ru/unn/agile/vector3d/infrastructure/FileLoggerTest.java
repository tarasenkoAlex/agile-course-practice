package ru.unn.agile.vector3d.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.fail;

public class FileLoggerTest {
    private static final String FILENAME = "./vector3dFileLoggerTests.log";
    private FileLogger logger;

    @Before
    public void setUp() {
        logger = new FileLogger(FILENAME);
    }

    @Test
    public void testLoggerCreated() {
        assertNotNull(logger);
    }

    @Test
    public void testFileExists() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void testPutSimpleMessage() {
        String testMessage = "Test message";
        logger.putLog(testMessage);
        String readMessage = logger.iterator().next();

        assertTrue(matchesPattern(".*" + testMessage + "$", testMessage).matches());
    }

    @Test
    public void testPutMessageWithArguments() {
        String testMessage = "Test message {0}";
        String testArg = "ARG";
        String compareMessage = "Test message ARG";

        logger.putLog(testMessage, testArg);
        String readMessage = logger.iterator().next();

        assertTrue(matchesPattern(".*" + compareMessage + "$", readMessage).matches());
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1",
                             "Test message 2"};

        logger.putLog(messages[0]);
        logger.putLog(messages[1]);

        int i = 0;
        Iterator<String> it = logger.iterator();
        while (it.hasNext()) {
            String readMessage = it.next();
            assertTrue(matchesPattern(".*" + messages[i] + "$", readMessage).matches());
            i++;
        }
    }

    private Matcher matchesPattern(final String regexp, final String search) {
        Pattern pattern = Pattern.compile(regexp);
        return pattern.matcher(search);
    }
}
