package ru.unn.agile.ColorConverter.infrastructure_colorConverter;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static ru.unn.agile.ColorConverter.infrastructure_colorConverter
        .ColorConverterRegexMatcher.matchesPattern;

public class ColorConverterLoggerTests {
    private static final String FILE = "./ColorConverterLogger.log";
    private ColorConverterLogger loggerFile;

    @Before
    public void setUp() {
        loggerFile = new ColorConverterLogger(FILE);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(loggerFile);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILE));
        } catch (FileNotFoundException e) {
            fail("File " + FILE + " can't be found!");
        }
    }

    @Test
    public void canWriteLogMessageToLogFile() {
        String mes = "Log Message";
        loggerFile.log(mes);

        String message = loggerFile.getLog().get(0);
        assertTrue(message.matches(".*Message.*"));
    }

    @Test
    public void canWriteLogMessage() {
        loggerFile.log("Log message 1");
        String msg = loggerFile.getLog().get(0);
        assertTrue(msg.matches(".*Log message 1*"));
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        loggerFile.log(testMessage);

        String message = loggerFile.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
