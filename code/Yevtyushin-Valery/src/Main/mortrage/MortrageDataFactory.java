package mortrage;

import java.security.InvalidParameterException;

/**
 * Created by Yevtyushin Valery on 15.11.2016.
 */
public class MortrageDataFactory {

    public static final double PERCENT_MAX = 100;

    private MortrageDataFactory() {
    }

    public static MortrageData getMortrageData(final double debt, final double years, final double percents) {


        if (years <= 0) {
            throw new InvalidParameterException("Incorrect data input");
        }

        if (debt <= 0) {
            throw new InvalidParameterException("Incorrect data input");
        }

        if (percents <= 0 || percents > PERCENT_MAX) {
            throw new InvalidParameterException("Incorrect data input");
        }

        return new MortrageData(debt, years, percents);
    }
}
