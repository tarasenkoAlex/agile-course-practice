package main.java.viewmodel;

import main.MortrageCalculator;
import main.mortrage.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ViewModel {
    private String debt;
    private String years;
    private String percents;
    private String totalSum;
    private String payment;
    private String overPayment;
    private String status;
    private boolean isCalcButtonEnabled;
    public static final int ENTER = 10;
    private MortrageDataBuilder mortrageDataBuilder;

    public ViewModel() {
        debt = "";
        years = "";
        percents = "";
        totalSum = "";
        payment = "";
        overPayment = "";
        status = Status.WAITING;
        isCalcButtonEnabled = false;
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
    }

    public void setYears(final String years) {
        this.years = years;
        changeStatus();
    }

    public void setPercents(final String percents) {
        this.percents = percents;
        changeStatus();
    }

    public void processKeyInTextField(final int keyCode) {
        changeStatus();

        if (keyCode == ENTER) {
            enterPressed();
        }
    }

    private void enterPressed() {

        if (isCalcButtonEnabled()) {
            calculate();
        }
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

    public void calculate() {
        if (status == Status.READY) {
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
