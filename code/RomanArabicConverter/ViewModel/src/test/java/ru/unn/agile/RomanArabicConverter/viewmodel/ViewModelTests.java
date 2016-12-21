package ru.unn.agile.RomanArabicConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        ILogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isConvertButtonDisableByDefault() {
        assertEquals(true, viewModel.getConverterBtnDisableProperty().get());
    }

    @Test
    public void whenRomanNumberIsQWERTY() {
        viewModel.setRomanNumberProperty("QWERTY");
        assertEquals(false, viewModel
                .validateRomanNumber(viewModel.getRomanNumberProperty().get()));
    }

    @Test
    public void whenRomanNumberIsIII() {
        viewModel.setRomanNumberProperty("III");
        assertEquals(true, viewModel
                .validateRomanNumber(viewModel.getRomanNumberProperty().get()));
    }

    @Test
    public void whenRomanNumberIs123() {
        viewModel.setRomanNumberProperty("123");
        assertEquals(false, viewModel
                .validateRomanNumber(viewModel.getRomanNumberProperty().get()));
    }

    @Test
    public void whenRomanNumberIsIIIIIII() {
        viewModel.setRomanNumberProperty("IIIIIII");
        assertEquals(false, viewModel
                .validateRomanNumber(viewModel.getRomanNumberProperty().get()));
    }

    @Test
    public void whenRomanNumberIsIIIQWERTY() {
        viewModel.setRomanNumberProperty("IIIQWERTY");
        assertEquals(false, viewModel
                .validateRomanNumber(viewModel.getRomanNumberProperty().get()));
    }

    @Test
    public void whenRomanNumberIsValidIsConvertBtnDisable() {
        viewModel.setRomanNumberProperty("III");
        assertEquals(false, viewModel.getConverterBtnDisableProperty().get());
    }

    @Test
    public void whenRomanNumberIsNotValidIsConvertBtnDisable() {
        viewModel.setRomanNumberProperty("QWERTY");
        assertEquals(true, viewModel.getConverterBtnDisableProperty().get());
    }

    @Test
    public void whenArabicNumberIsQWERTY() {
        viewModel.setArabicNumberProperty("QWERTY");
        assertEquals(false, viewModel
                .validateArabicNumber(viewModel.getArabicNumberProperty().get()));
    }

    @Test
    public void whenArabicNumberIs123() {
        viewModel.setArabicNumberProperty("123");
        assertEquals(true, viewModel
                .validateArabicNumber(viewModel.getArabicNumberProperty().get()));
    }

    @Test
    public void whenArabicNumberIsIII() {
        viewModel.setArabicNumberProperty("III");
        assertEquals(false, viewModel
                .validateArabicNumber(viewModel.getArabicNumberProperty().get()));
    }

    @Test
    public void whenArabicNumberIsMinus5() {
        viewModel.setArabicNumberProperty("-5");
        assertEquals(false, viewModel
                .validateArabicNumber(viewModel.getArabicNumberProperty().get()));
    }

    @Test
    public void whenArabicNumberIsValidIsConvertBtnDisable() {
        viewModel.setArabicNumberProperty("123");
        assertEquals(false, viewModel.getConverterBtnDisableProperty().get());
    }

    @Test
    public void whenChooseRBArabicToRoman() {
        viewModel.setRBArabToRomChooseProperty(true);
        assertEquals(true, viewModel.getRBArabToRomChooseProperty().get());
    }

    @Test
    public void whenNotChooseRBArabicToRoman() {
        viewModel.setRBArabToRomChooseProperty(false);
        assertEquals(false, viewModel.getRBArabToRomChooseProperty().get());
    }

    @Test
    public void whenChooseRBRomanToArabic() {
        viewModel.setRBRomToArabChooseProperty(true);
        assertEquals(true, viewModel.getRBRomToArabChooseProperty().get());
    }

    @Test
    public void whenNotChooseRBRomanToArabic() {
        viewModel.setRBRomToArabChooseProperty(false);
        assertEquals(false, viewModel.getRBRomToArabChooseProperty().get());
    }

    @Test
    public void whenChooseRBArabicToRomanTFRomDisable() {
        viewModel.setRBArabToRomChooseProperty(true);
        assertEquals(true, viewModel.getTFRomDisableProperty().get());
    }

    @Test
    public void whenChooseRBRomanToArabicTFRomNotDisable() {
        viewModel.setRBRomToArabChooseProperty(true);
        assertEquals(false, viewModel.getTFRomDisableProperty().get());
    }

    @Test
    public void whenChooseRBArabicToRomanTFArabNotDisable() {
        viewModel.setRBArabToRomChooseProperty(true);
        assertEquals(false, viewModel.getTFArabDisableProperty().get());
    }

    @Test
    public void whenChooseRBRomanToArabicTFArabDisable() {
        viewModel.setRBRomToArabChooseProperty(true);
        assertEquals(true, viewModel.getTFArabDisableProperty().get());
    }

    @Test
    public void whenConvertArabicToRomanNumberArabicIs122StatusIsREADY() {
        viewModel.setRBArabToRomChooseProperty(true);
        viewModel.setArabicNumberProperty("122");
        assertEquals(Status.READY.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void whenConvertArabicToRomanNumberArabicIsQWERTYStatusIsBADFORMAT() {
        viewModel.setRBArabToRomChooseProperty(true);
        viewModel.setArabicNumberProperty("QWERTY");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void whenConvertArabicToRomanNumberArabicIsEmptyStatusIsWAITING() {
        viewModel.setRBArabToRomChooseProperty(true);
        viewModel.setArabicNumberProperty("");
        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void whenConvertRomanToArabicNumberRomanIsIIIStatusIsREADY() {
        viewModel.setRBRomToArabChooseProperty(true);
        viewModel.setRomanNumberProperty("III");
        assertEquals(Status.READY.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void whenConvertRomanToArabicNumberRomanIsQWERTYStatusIsBADFORMAT() {
        viewModel.setRBRomToArabChooseProperty(true);
        viewModel.setRomanNumberProperty("QWERTY");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void whenConvertRomanToArabicNumberRomanIsEmptyStatusIsWAITING() {
        viewModel.setRBRomToArabChooseProperty(true);
        viewModel.setRomanNumberProperty("");
        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void whenConvertArabicNumber5ToRomanNumberResultIsV() {
        viewModel.setRBArabToRomChooseProperty(true);
        viewModel.setArabicNumberProperty("5");
        viewModel.convertNumber();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatusProperty().get());
        assertEquals("V", viewModel.getRomanNumberProperty().get());
    }

    @Test
    public void whenConvertArabicNumberQWERTYToRomanNumberResultStatusIsBADFORMAT() {
        viewModel.setRBArabToRomChooseProperty(true);
        viewModel.setArabicNumberProperty("QWERTY");
        viewModel.convertNumber();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void whenConvertRomanNumberVIToArabicNumberResultIs6() {
        viewModel.setRBRomToArabChooseProperty(true);
        viewModel.setRomanNumberProperty("VI");
        viewModel.convertNumber();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatusProperty().get());
        assertEquals("6", viewModel.getArabicNumberProperty().get());
    }

    @Test
    public void whenConvertRomanNumberQWERTYToArabicNumberResultStatusIsBADFORMAT() {
        viewModel.setRBRomToArabChooseProperty(true);
        viewModel.setRomanNumberProperty("QWERTY");
        viewModel.convertNumber();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void isLogEmptyInBegining() {
        List<String> logs = viewModel.getLogger().getLog();
        assertEquals(true, logs.isEmpty());
    }

    @Test
    public void logWhenConvertSideChangeToRoman() {
        viewModel.setRBArabToRomChooseProperty(false);
        List<String> logs = viewModel.getLogger().getLog();
        String log = !logs.isEmpty() ? logs.get(0) : "";
        assertTrue(log.matches(".*Convert from roman number chosen.*"));
    }

    @Test
    public void whenViewModelInitializeWithoutParamsLoggerIsNull() {
        ViewModel vm = new ViewModel();
        ILogger logger = vm.getLogger();
        assertNull(logger);
    }

    @Test
    public void getLogsWhenLogSmth() {
        viewModel.setRBArabToRomChooseProperty(false);
        String log = viewModel.getLogs();
        assertTrue(log.matches(".*Convert from roman number chosen\n.*"));
    }

    @Test
    public void logWhenConvertSideChangeToArabic() {
        viewModel.setRBArabToRomChooseProperty(false);
        viewModel.setRBArabToRomChooseProperty(true);
        List<String> logs = viewModel.getLogger().getLog();
        String log = !logs.isEmpty() ? logs.get(logs.size() - 1) : "";
        assertTrue(log.matches(".*Convert from arabic number chosen.*"));
    }

    @Test
    public void whenRomanTfLoseFocusLog() {
        viewModel.setFocusRomanTfProperty(true);
        viewModel.setRomanNumberProperty("XII");
        viewModel.setFocusRomanTfProperty(false);

        List<String> logs = viewModel.getLogger().getLog();
        String log = !logs.isEmpty() ? logs.get(0) : "";
        assertTrue(log.matches(".*User input roman number XII.*"));
    }

    @Test
    public void whenArabicTfLoseFocusLog() {
        viewModel.setFocusArabicTfProperty(true);
        viewModel.setArabicNumberProperty("XII");
        viewModel.setFocusArabicTfProperty(false);

        List<String> logs = viewModel.getLogger().getLog();
        String log = !logs.isEmpty() ? logs.get(0) : "";
        assertTrue(log.matches(".*User input arabic number XII.*"));
    }

    @Test
    public void whenConvertToRomanBtnClicked() {
        actionChainForConvert(true, "1234");

        List<String> logs = viewModel.getLogger().getLog();
        String log = !logs.isEmpty() ? logs.get(logs.size() - 1) : "";
        assertTrue(log.matches(".*Converted from arabic to roman. Result MCCXXXIV.*"));
    }

    @Test
    public void whenConvertToArabicBtnClicked() {
        actionChainForConvert(false, "MCCXXXIV");

        List<String> logs = viewModel.getLogger().getLog();
        String log = !logs.isEmpty() ? logs.get(logs.size() - 1) : "";
        assertTrue(log.matches(".*Converted from roman to arabic. Result 1234.*"));
    }

    private void actionChainForConvert(final boolean arabToRomanChosen, final String inputNumber) {
        viewModel.setRBArabToRomChooseProperty(arabToRomanChosen);
        if (arabToRomanChosen) {
            viewModel.setArabicNumberProperty(inputNumber);
        } else {
            viewModel.setRomanNumberProperty(inputNumber);
        }
        viewModel.convertNumber();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLoggerIsNull() {
        ViewModel vm = new ViewModel(null);
    }
}
