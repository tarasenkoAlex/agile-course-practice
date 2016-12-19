package ru.unn.agile.CurrencyConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.model.*;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() throws IOException {
        viewModel = new ViewModel(new FakeLogger());
    }
    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canCreateViewModelWithDefaultConstructor() {
        assertNotNull(new ViewModel());
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWithNullLogger() {
        viewModel = new ViewModel(null);
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.amountProperty().get());
        assertEquals(Constants.RUBLE, viewModel.fromCurrencyProperty().get());
        assertEquals(Constants.DOLLAR, viewModel.toCurrencyProperty().get());
        assertEquals("", viewModel.resultingValueProperty().get());
        assertEquals(MessageStatus.WAITING.getValue(), viewModel.messageProperty().get());
    }
    @Test
    public void canMessageWaitingFormatWithEmptyAmount() throws IOException {
        viewModel.convert();
        assertEquals(MessageStatus.WAITING.getValue(), viewModel.messageProperty().get());
    }
    @Test
    public void canMessageReadyFormatWithFilledAmount() {
        setInputData();
        assertEquals(MessageStatus.READY.getValue(), viewModel.messageProperty().get());
    }
    @Test
    public void canMessageSuccessFormatAfterConvert() throws IOException {
        setInputData();
        viewModel.convert();
        assertEquals(MessageStatus.SUCCESS.getValue(), viewModel.messageProperty().get());
    }
    @Test
    public void canMessageFailedFormat() {
        viewModel.amountProperty().set("one");
        assertEquals(MessageStatus.FAILED.getValue(), viewModel.messageProperty().get());
    }
    @Test
    public void canMessageFailedFormatForNegativeAmount() {
        viewModel.amountProperty().set("-78");
        assertEquals(MessageStatus.FAILED.getValue(), viewModel.messageProperty().get());
    }
    @Test
    public void canSetRUBLEFromCurrency() {
        viewModel.fromCurrencyProperty().set(Constants.RUBLE);
        assertEquals(Constants.RUBLE, viewModel.fromCurrencyProperty().get());
    }
    @Test
    public void canSetRUBLEToCurrency() {
        viewModel.toCurrencyProperty().set(Constants.RUBLE);
        assertEquals(Constants.RUBLE, viewModel.toCurrencyProperty().get());
    }
    @Test
    public void canSetDOLLARFromCurrency() {
        viewModel.fromCurrencyProperty().set(Constants.DOLLAR);
        assertEquals(Constants.DOLLAR, viewModel.fromCurrencyProperty().get());
    }
    @Test
    public void canSetDOLLARToCurrency() {
        viewModel.toCurrencyProperty().set(Constants.DOLLAR);
        assertEquals(Constants.DOLLAR, viewModel.toCurrencyProperty().get());
    }
    @Test
    public void canSetEUROFromCurrency() {
        viewModel.fromCurrencyProperty().set(Constants.EURO);
        assertEquals(Constants.EURO, viewModel.fromCurrencyProperty().get());
    }
    @Test
    public void canSetEUROToCurrency() {
        viewModel.toCurrencyProperty().set(Constants.EURO);
        assertEquals(Constants.EURO, viewModel.toCurrencyProperty().get());
    }
    @Test
    public void canSetHRYVNAFromCurrency() {
        viewModel.fromCurrencyProperty().set(Constants.HRYVNA);
        assertEquals(Constants.HRYVNA, viewModel.fromCurrencyProperty().get());
    }
    @Test
    public void canSetHRYVNAToCurrency() {
        viewModel.toCurrencyProperty().set(Constants.HRYVNA);
        assertEquals(Constants.HRYVNA, viewModel.toCurrencyProperty().get());
    }
    @Test
    public void canSetYENFromCurrency() {
        viewModel.fromCurrencyProperty().set(Constants.YEN);
        assertEquals(Constants.YEN, viewModel.fromCurrencyProperty().get());
    }
    @Test
    public void canSetYENToCurrency() {
        viewModel.toCurrencyProperty().set(Constants.YEN);
        assertEquals(Constants.YEN, viewModel.toCurrencyProperty().get());
    }
    @Test
    public void canSetFRANCFromCurrency() {
        viewModel.fromCurrencyProperty().set(Constants.FRANC);
        assertEquals(Constants.FRANC, viewModel.fromCurrencyProperty().get());
    }
    @Test
    public void canSetFRANCToCurrency() {
        viewModel.toCurrencyProperty().set(Constants.FRANC);
        assertEquals(Constants.FRANC, viewModel.toCurrencyProperty().get());
    }
    @Test
    public void checkDisabledButtonForEmptyAmount() {
        assertTrue(viewModel.convertionDisabledProperty().get());
    }
    @Test
    public void checkDisabledButtonForCorrectAmount() {
        viewModel.amountProperty().set("32");
        assertFalse(viewModel.convertionDisabledProperty().get());
    }
    @Test
    public void checkDisabledButtonForIncorrectAmount() {
        viewModel.amountProperty().set("Two");
        assertTrue(viewModel.convertionDisabledProperty().get());
    }
    @Test
    public void checkDisabledButtonForNegativeAmount() {
        viewModel.amountProperty().set("-98");
        assertTrue(viewModel.convertionDisabledProperty().get());
    }
    @Test
    public void checkDisabledButtonAfterConvert() throws IOException {
        viewModel.amountProperty().set("32");
        viewModel.convert();
        assertFalse(viewModel.convertionDisabledProperty().get());
    }
    @Test
    public void convertFromRubleToDollar() throws IOException {
        viewModel.amountProperty().set("125.24");
        viewModel.fromCurrencyProperty().set(Constants.RUBLE);
        viewModel.toCurrencyProperty().set(Constants.DOLLAR);
        viewModel.convert();
        assertEquals("2.0", viewModel.resultingValueProperty().get());
    }
    @Test
    public void checkTheFirstCurrencyInBox() {
        assertEquals(Constants.values()[0], viewModel.getCurrencies().get(0));
    }
    @Test
    public void checkTheSecondCurrencyInBox() {
        assertEquals(Constants.values()[1], viewModel.getCurrencies().get(1));
    }
    @Test
    public void checkTheThirdCurrencyInBox() {
        assertEquals(Constants.values()[2], viewModel.getCurrencies().get(2));
    }
    @Test
    public void checkTheForthCurrencyInBox() {
        assertEquals(Constants.values()[3], viewModel.getCurrencies().get(3));
    }
    @Test
    public void checkTheFifthCurrencyInBox() {
        assertEquals(Constants.values()[4], viewModel.getCurrencies().get(4));
    }
    @Test
    public void checkTheSixthCurrencyInBox() {
        assertEquals(Constants.values()[5], viewModel.getCurrencies().get(5));
    }
    @Test
    public void checkFirstValueFromCurrenciesProperty() {
        assertEquals(Constants.values()[0], viewModel.currenciesProperty().get().get(0));
    }
    @Test
    public void checkSecondValueFromCurrenciesProperty() {
        assertEquals(Constants.values()[1], viewModel.currenciesProperty().get().get(1));
    }
    @Test
    public void checkThirdValueFromCurrenciesProperty() {
        assertEquals(Constants.values()[2], viewModel.currenciesProperty().get().get(2));
    }
    @Test
    public void checkForthValueFromCurrenciesProperty() {
        assertEquals(Constants.values()[3], viewModel.currenciesProperty().get().get(3));
    }
    @Test
    public void checkFifthValueFromCurrenciesProperty() {
        assertEquals(Constants.values()[4], viewModel.currenciesProperty().get().get(4));
    }
    @Test
    public void checkSixthValueFromCurrenciesProperty() {
        assertEquals(Constants.values()[5], viewModel.currenciesProperty().get().get(5));
    }
    @Test
    public void checkStringResultingValue() throws IOException {
        setInputData();
        viewModel.convert();
        assertEquals("125.24", viewModel.getResultingValue());
    }
    @Test
    public void checkStringMessage() throws IOException {
        setInputData();
        viewModel.convert();
        assertEquals(MessageStatus.SUCCESS.getValue(), viewModel.getMessage());
    }
    @Test
    public void isButtonDisabledWithEmptyData() {
        assertTrue(viewModel.getConvertionDisabled());
    }

    public void setInputData() {
        viewModel.amountProperty().set("2");
        viewModel.fromCurrencyProperty().set(Constants.DOLLAR);
        viewModel.toCurrencyProperty().set(Constants.RUBLE);
    }

    @Test
    public void emptyLog() throws IOException {
        assertNotEquals(null, viewModel.getLog());
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void logChangedValue() throws IOException {
        viewModel.amountProperty().set("2");
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void logConvert() throws IOException {
        viewModel.amountProperty().set("2");
        viewModel.convert();
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void checkLogMessage() throws IOException {
        setInputData();
        assertTrue(viewModel.getLog().get(0).contains("Value changed to 2"));
    }

    @Test
    public void checkLogMessageAfterConvert() throws IOException {
        setInputData();
        viewModel.convert();
        assertTrue(viewModel.getLog().get(viewModel.getLog().size() - 1)
                .contains("Converted 2 DOLLAR to 125.24 RUBLE"));
    }

    @Test
    public void logFromCurrency() throws IOException {
        viewModel.fromCurrencyProperty().set(Constants.DOLLAR);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void checkLogMessageAfterFromCurrencyChanged() throws IOException {
        viewModel.fromCurrencyProperty().set(Constants.DOLLAR);
        assertTrue(viewModel.getLog().get(0).contains("FromCurrency changed to DOLLAR"));
    }

    @Test
    public void checkLogMessageAfterToCurrencyChanged() throws IOException {
        viewModel.toCurrencyProperty().set(Constants.EURO);
        assertTrue(viewModel.getLog().get(0).contains("ToCurrency changed to EURO"));
    }

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Test
    public void getLogProperty() {
        setInputData();
        assertTrue(viewModel.logsProperty().toString().contains("Value changed to 2"));
    }

    @Test
    public void getLogs() {
        setInputData();
        assertTrue(viewModel.getLogs().contains("Value changed to 2"));
    }
}
