package ru.unn.agile.CurrencyConverter.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static ru.unn.agile.CurrencyConverter.infrastructure.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
    private TxtLogger txtLogger;
    private static final String FILENAME = "./TxtLoggerTests.log";

    @Before
    public void setUp() throws IOException {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void createLogger() {
        assertNotNull(txtLogger);
    }

    @Test
    public void checkFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void checkMessage() throws IOException {
        String message = "Test message";
        txtLogger.log(message);
        assertThat(txtLogger.getLog().get(0), matchesPattern(".*" + message + "$"));
    }

    @Test
    public void checkData() throws IOException {
        String message = "Test message";
        txtLogger.log(message);
        assertThat(txtLogger.getLog().get(0),
                matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

    @Test
    public void checkSeveralMessages() throws IOException {
        String[] messages = {"Test message 1", "Test message 2"};
        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);
        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesPattern(".* " + messages[i] + "$"));
        }
    }
}
