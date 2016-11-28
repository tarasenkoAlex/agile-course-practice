package ru.unn.agile.personalfinance.view.controls;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

final class FXMLHelper {
    public static <T> void load(final T controller, final String fxmlResourceName) {
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
