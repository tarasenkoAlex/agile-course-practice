import mortrage.MortrageData;
import mortrage.MortrageDataFactory;

public class MortrageCalculator {
    public static final double MONTH_IN_THE_YEAR = 12;

    private MortrageCalculator() {
    }

    public static double countPayment(MortrageData mortrageData) {
        double percentBet = mortrageData.getPercents() / MortrageDataFactory.PERCENT_MAX;
        double mortragePercent = mortrageData.getDebt() * percentBet
                * (1 + 1 / (Math.pow(1 + percentBet, mortrageData.getYears())));
        return (mortragePercent / MONTH_IN_THE_YEAR);
    }

    public static double countTotalSum(MortrageData mortrageData) {
        double payment = countPayment(mortrageData);
        return payment * mortrageData.getYears() * MONTH_IN_THE_YEAR;
    }

    public static double countOverpayment(MortrageData mortrageData) {
        double totalSum = countTotalSum(mortrageData);
        return totalSum - mortrageData.getDebt();
    }
}