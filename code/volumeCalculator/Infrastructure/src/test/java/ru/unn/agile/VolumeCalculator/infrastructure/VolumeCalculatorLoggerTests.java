package ru.unn.agile.VolumeCalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

/**
 * Created by Elena on 12/20/2016.
 */
public class VolumeCalculatorLoggerTests {
    private static final String FILE_NAME = "./lab3-Volume-Calculator-Logger.log";
    private VolumeCalculatorLogger logger;

    @Before
    public void setUp() {
        logger = new VolumeCalculatorLogger(FILE_NAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILE_NAME + " wasn't found! Please, check!");
        }
    }

    @Test
    public void canWriteMessageLog() {
        String testMessage = "Log message ";
        logger.addLog(testMessage);
        assertEquals(1, logger.getLog().size());
    }

    @Test
    public void canWriteMessageLog2() {
        String testMessage = "Log message ";
        logger.addLog(testMessage);
        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*Log message.*"));
    }

    @Test
    public void canWriteSeveralLogMessages() {
        String[] messages = {"Log message 1", "Log message 2"};
        logger.addLog(messages[0]);
        logger.addLog(messages[1]);
        List<String> actualMessages = logger.getLog();
        int num = 1;
        for (String elem: actualMessages) {
            assertTrue((elem + num).matches(".*Log message.*"));
            num++;
        }
    }

    @Test
    public void canWriteSeveralLogMessages2() {
        String[] messages = {"Log message 1", "Log message 2"};
        logger.addLog(messages[0]);
        logger.addLog(messages[1]);
        assertEquals(2, logger.getLog().size());
    }

    @Test
    public void checkDateAndTimeInLogMessage() {
        String testMessage = "Log message";
        logger.addLog(testMessage);
        String message = logger.getLog().get(0);
        Pattern p = Pattern.compile("^\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\] : .*");
        Matcher m = p.matcher(message);
        assertTrue(m.matches());
    }
}
