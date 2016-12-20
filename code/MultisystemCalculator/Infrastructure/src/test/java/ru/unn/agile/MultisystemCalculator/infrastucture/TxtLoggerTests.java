package ru.unn.agile.MultisystemCalculator.infrastucture;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static ru.unn.agile.MultisystemCalculator.infrastucture.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests-lab3.log";
    private TxtLogger logger;

    @Before
    public void setUp() throws IOException {
        logger = new TxtLogger(FILENAME);
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
    public void canWriteLogMessage() throws IOException {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() throws IOException {
        String[] messages = {"One message", "Two message"};

        logger.log(messages[0]);
        logger.log(messages[1]);

        List<String> currentMessages = logger.getLog();
        for (int i = 0; i < currentMessages.size(); i++) {
            assertThat(currentMessages.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() throws IOException {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
