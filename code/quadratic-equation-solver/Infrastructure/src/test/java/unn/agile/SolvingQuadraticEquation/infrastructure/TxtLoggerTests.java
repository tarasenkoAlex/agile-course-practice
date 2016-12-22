package unn.agile.SolvingQuadraticEquation.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static unn.agile.SolvingQuadraticEquation.infrastructure.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
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
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void writeSeveralLogMessage() {
        String[] messages = {"message 1", "message 2"};

        logger.makeLog(messages[0]);
        logger.makeLog(messages[1]);

        List<String> actualMessages = logger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void checkLogContainDateAndTime() {
        String messageForTest = "message";

        logger.makeLog(messageForTest);

        String message = logger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
