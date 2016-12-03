package ru.unn.agile.personalfinance.view.utils;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public final class FXMLHelper {
    public static <T> void applyTemplate(final T controller, final String fxmlResourceName) {
        URL fxmlUrl = controller.getClass().getResource(fxmlResourceName);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        fxmlLoader.setController(controller);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FXMLHelper() {
    }
}
