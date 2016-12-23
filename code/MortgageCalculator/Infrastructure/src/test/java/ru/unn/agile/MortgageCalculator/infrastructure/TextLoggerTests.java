package ru.unn.agile.MortgageCalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.MortgageCalculator.infrastructure.RegexMatcher.matchesTemplate;

public class TextLoggerTests {
    private static final String FILE_NAME = "./VeselovTxtLoggerTests-lab3.log";
    private TextLogger textLogger;

    @Before
    public void setUp() {
        textLogger = new TextLogger(FILE_NAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(textLogger);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException exc) {
            fail("File " + FILE_NAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test";

        textLogger.log(testMessage);

        String message = textLogger.getLogList().get(0);
        assertThat(message, matchesTemplate(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test 1", "Test 2"};

        textLogger.log(messages[0]);
        textLogger.log(messages[1]);

        List<String> actualMessages = textLogger.getLogList();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesTemplate(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test";

        textLogger.log(testMessage);

        String message = textLogger.getLogList().get(0);
        assertThat(message, matchesTemplate("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
