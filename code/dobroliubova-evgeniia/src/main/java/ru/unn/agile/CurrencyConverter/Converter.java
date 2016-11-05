package ru.unn.agile.CurrencyConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jane on 03.11.2016.
 */
public class Converter {
    private static Map<String, Double> currencyMap = new HashMap<String, Double>(){{
        put("theSame", 1.0);
        put("rubleToDollar", 62.6200);
        put("rubleToEuro", 69.1254);
        put("rubleToHryvna", 24.3255);
        put("rubleToYen", 60.4592);
        put("rubleToFranc", 63.5256);
        put("dollarToRuble", 1/62.6200);
        put("euroToRuble", 1/69.1254);
        put("hryvnaToRuble", 1/24.3255);
        put("yenToRuble", 1/60.4592);
        put("francToRuble", 1/63.5256);
        put("dollarToEuro", 1.1038);
        put("euroToDollar", 0.9058);
        put("dollarToHryvna", 0.3884);
        put("hryvnaToDollar", 2.5742);
        put("dollarToYen", 0.9654);
        put("yenToDollar", 1.0357);
        put("dollarToFranc", 1.0144);
        put("francToDollar", 0.9857);
        put("euroToHryvna", 0.3519);
        put("hryvnaToEuro", 2.8416);
        put("euroToYen", 0.8746);
        put("yenToEuro", 1.1433);
        put("euroToFranc", 0.9189);
        put("francToEuro", 1.0881);
        put("hryvnaToYen", 2.4854);
        put("yenToHryvna", 0.4023);
        put("hryvnaToFranc", 2.6114);
        put("francToHryvna", 0.3829);
        put("yenToFranc", 1.0507);
        put("francToYen", 0.9517);
    }};

    public double execute(double amount, String type) {
        return amount/currencyMap.get(type);
    }
}
