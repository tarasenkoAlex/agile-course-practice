package unn.agile.SolvingQuadraticEquation.infrastructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertNotNull;


public class FileLoggerTests {
    private Pattern messagePattern;
    private Matcher messageMatcher;
    private static final String FILE_NAME_FOR_LOG = "./TxtLogger_Tests-lab3.makeLog";
    private FileLogger logger;

    @Before
    public void setUp() {
        logger = new FileLogger(FILE_NAME_FOR_LOG);
    }

    @Test
    public void makeLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void makeFileOnDiskForLog() {
        try {
            new BufferedReader(new FileReader(FILE_NAME_FOR_LOG));
        } catch (FileNotFoundException e) {
            fail("File " + FILE_NAME_FOR_LOG + " wasn't found!");
        }
    }

    @Test
    public void writeLogMessage() {
        String testMessage = "message";

        logger.makeLog(testMessage);

        String message = logger.getLog().get(0);
        messagePattern = Pattern.compile(".*" + testMessage + "$");
        messageMatcher = messagePattern.matcher(message);
        assertTrue(messageMatcher.matches());
    }

    @Test
    public void writeSeveralLogMessage() {
        String[] messages = {"message 1", "message 2"};

        logger.makeLog(messages[0]);
        logger.makeLog(messages[1]);

        List<String> actualMessages = logger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            messagePattern = Pattern.compile(".*" + messages[i] + "$");
            messageMatcher = messagePattern.matcher(actualMessages.get(i));
            assertTrue(messageMatcher.matches());
        }
    }

    @Test
    public void checkLogContainDateAndTime() {
        String messageForTest = "message";

        logger.makeLog(messageForTest);

        String message = logger.getLog().get(0);
        messagePattern = Pattern.compile("^\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\] > .*");
        messageMatcher = messagePattern.matcher(message);
        assertTrue(messageMatcher.matches());
    }
}
