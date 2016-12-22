package ru.unn.agile.MortgageCalculator.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.MortgageCalculator.model.MortrageCalculator;
import ru.unn.agile.MortgageCalculator.model.MortrageData;
import ru.unn.agile.MortgageCalculator.model.MortrageDataBuilder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ViewModel {
    private ILogger logger;
    private String debt;
    private String years;
    private String percents;
    private String totalSum;
    private String payment;
    private String overPayment;
    private String status;
    private final StringProperty logs = new SimpleStringProperty();
    private boolean isCalcButtonEnabled;
    private boolean isInputChanged;
    public static final int ENTER_KEY_CODE = 10;
    private MortrageDataBuilder mortrageDataBuilder;

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    private void init() {
        debt = "";
        years = "";
        percents = "";
        totalSum = "";
        payment = "";
        overPayment = "";
        status = Status.WAITING;
        isCalcButtonEnabled = false;
        isInputChanged = true;
    }

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
    }

    public String getDebt() {
        return debt;
    }

    public String getYears() {
        return years;
    }

    public String getPercents() {
        return percents;
    }

    public String getTotalSum() {
        return totalSum;
    }

    public String getPayment() {
        return payment;
    }

    public String getOverPayment() {
        return overPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setDebt(final String debt) {
        this.debt = debt;
        changeStatus();
        isInputChanged = true;
    }

    public void setYears(final String years) {
        this.years = years;
        changeStatus();
        isInputChanged = true;
    }

    public void setPercents(final String percents) {
        this.percents = percents;
        changeStatus();
        isInputChanged = true;
    }

    private void clearResultFields() {
        totalSum = "";
        payment = "";
        overPayment = "";
    }

    void changeStatus() {
        clearResultFields();
        try {
            if (!debt.isEmpty()) {
                parseInput(debt);
            }
            if (!years.isEmpty()) {
                parseInput(years);
            }
            if (!percents.isEmpty()) {
                parseInput(percents);
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isCalcButtonEnabled = false;
            return;
        }
        if (debt.isEmpty() || years.isEmpty() || percents.isEmpty()) {
            status = Status.WAITING;
            isCalcButtonEnabled = false;
            return;
        } else if (checkNegativeNumberInput()) {
            status = Status.BAD_FORMAT;
            isCalcButtonEnabled = false;
            return;
        } else {
            status = Status.READY;
            isCalcButtonEnabled = true;
        }
    }

    boolean checkNegativeNumberInput() {
        if (parseInput(debt) < 0) {
            return true;
        } else if (parseInput(years) < 0) {
            return true;
        } else if (parseInput(percents) < 0) {
            return true;
        }
        return parseInput(percents) > MortrageDataBuilder.PERCENT_MAX;
    }

    double parseInput(final String input) {
        return Double.parseDouble(input);
    }

    private String editingFinishedLogMessage() {
        String message = LogMessages.EDITING_FINISHED
                + "Input arguments are: ["
                + debt + "; "
                + years + "; "
                + percents  + "]";

        return message;
    }

    public void processKeyInTextField(final int keyCode) {
        changeStatus();

        if (keyCode == ENTER_KEY_CODE) {
            enterPressed();
        }
    }

    private void enterPressed() {
        logInputParams();

        if (isCalcButtonEnabled()) {
            calculate();
        }
    }

    private void logInputParams() {
        if (!isInputChanged) {
            return;
        }

        logger.log(editingFinishedLogMessage());
        isInputChanged = false;
    }

    public void focusLost() {
        logInputParams();
    }

    public void calculate() {
        if (status == Status.READY) {
            logger.log(calculateLogMessage());
            mortrageDataBuilder = new MortrageDataBuilder();
            MortrageData mortrageData = mortrageDataBuilder
                    .setDebt(parseInput(debt))
                    .setYears(parseInput(years))
                    .setPercents(parseInput(percents))
                    .build();
            totalSum = goToOutputFormat(MortrageCalculator.countTotalSum(mortrageData));
            payment = goToOutputFormat(MortrageCalculator.countPayment(mortrageData));
            overPayment = goToOutputFormat(MortrageCalculator.countOverpayment(mortrageData));
        }
    }

    private String calculateLogMessage() {
        String message =
                LogMessages.CALCULATE_WAS_PRESSED + "Arguments"
                        + ": Debt = " + debt
                        + "; Years = " + years
                        + "; Percents = " + percents
                        + ".";

        return message;
    }

    String goToOutputFormat(final double output) {
        BigDecimal tmp = new BigDecimal(String.valueOf(output));
        return String.valueOf(tmp.setScale(2, RoundingMode.UP));
    }

    public boolean isCalcButtonEnabled() {
        return isCalcButtonEnabled;
    }

    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate'";
        public static final String BAD_FORMAT = "Bad format";

        private Status() { }
    }
}

final class LogMessages {
    public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
    public static final String EDITING_FINISHED = "Updated input. ";

    private LogMessages() { }
}
