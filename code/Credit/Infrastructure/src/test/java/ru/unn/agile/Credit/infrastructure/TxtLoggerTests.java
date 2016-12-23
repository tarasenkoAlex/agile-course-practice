package ru.unn.agile.Credit.infrastructure;

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
import static ru.unn.agile.Credit.infrastructure.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
    private static final String LOGFILENAME = "./TxtLoggerTests-lab3-legacy.log";
    private TxtLogger logger;

    @Before
    public void setUp() {
        logger = new TxtLogger(LOGFILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(LOGFILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + LOGFILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        logger.log(messages[0]);
        logger.log(messages[1]);

        List<String> loggerActualMessages = logger.getLog();
        for (int i = 0; i < loggerActualMessages.size(); i++) {
            assertThat(loggerActualMessages.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }



    @Test
    public void cannotLog() throws IOException {
        logger.getWriter().close();
        logger.log("123");
    }

    @Test
    public void cannotGetLog() {
        logger.getLog(true);
    }

    @Test
    public void cannotCreateLogger() {
        logger = new TxtLogger(null);
    }
}
