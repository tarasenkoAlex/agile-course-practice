package main;

import main.mortrage.MortrageData;
import main.mortrage.MortrageDataBuilder;

public final class MortrageCalculator {
    public static final double MONTH_IN_THE_YEAR = 12;

    private MortrageCalculator() {
    }

    public static double countPayment(final MortrageData mortrageData) {
        double percentBet = mortrageData.getPercents() / MortrageDataBuilder.PERCENT_MAX;
        double mortragePercent = mortrageData.getDebt() * percentBet
                * (1 + 1 / (Math.pow(1 + percentBet, mortrageData.getYears())));
        return mortragePercent / MONTH_IN_THE_YEAR;
    }

    public static double countTotalSum(final MortrageData mortrageData) {
        double payment = countPayment(mortrageData);
        return payment * mortrageData.getYears() * MONTH_IN_THE_YEAR;
    }

    public static double countOverpayment(final MortrageData mortrageData) {
        double totalSum = countTotalSum(mortrageData);
        return totalSum - mortrageData.getDebt();
    }
}
