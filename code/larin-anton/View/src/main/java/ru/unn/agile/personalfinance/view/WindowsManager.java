package ru.unn.agile.personalfinance.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Stack;

final class WindowsManager {
    private static final int TRANSACTIONS_WINDOW_WIDTH  = 700;
    private static final int TRANSACTIONS_WINDOW_HEIGHT = 400;
    private static final int ADD_ACCOUNT_WINDOW_WIDTH   = 500;
    private static final int ADD_ACCOUNT_WINDOW_HEIGHT  = 300;

    private static final Object LOCK = new Object();
    private static WindowsManager windowsManager;

    private final Stack<Stage> stagesHstory = new Stack<>();

    private WindowsManager() { }

    static WindowsManager getInstance() {
        // Using synchronized block instead of
        // synchronized method to prevent PMD violation
        synchronized (LOCK) {
            if (windowsManager == null) {
                windowsManager = new WindowsManager();
            }
        }
        return windowsManager;
    }

    void showTransactionsView(final Stage stage) {
        getView("transactions.fxml")
                .setTitle("My Wallet")
                .setWidth(TRANSACTIONS_WINDOW_WIDTH)
                .setHeight(TRANSACTIONS_WINDOW_HEIGHT)
                .show(stage);
        stagesHstory.push(stage);
    }

    void showAddAccountView(final Stage stage) {
        getView("add-account.fxml")
                .setTitle("Add new account")
                .setWidth(ADD_ACCOUNT_WINDOW_WIDTH)
                .setHeight(ADD_ACCOUNT_WINDOW_HEIGHT)
                .show(stage);
        stagesHstory.push(stage);
    }

    void showAddTransactionView(final Stage stage) {
        getView("add-transaction.fxml")
                .setTitle("Add new transaction")
                .setWidth(ADD_ACCOUNT_WINDOW_WIDTH)
                .setHeight(ADD_ACCOUNT_WINDOW_HEIGHT)
                .show(stage);
        stagesHstory.push(stage);
    }

    void goBack() {
        Stage currentStage = stagesHstory.pop();
        currentStage.close();
    }

    private ViewBuilder getView(final String fxmlSource) {
        return new ViewBuilder(fxmlSource);
    }

    class ViewBuilder {
        private final URL rootUrl;
        private String title;
        private int width;
        private int height;

        ViewBuilder(final String fxmlSource) {
            rootUrl = getClass().getResource(fxmlSource);
        }

        ViewBuilder setTitle(final String title) {
            this.title = title;
            return this;
        }

        ViewBuilder setWidth(final int width) {
            this.width = width;
            return this;
        }

        ViewBuilder setHeight(final int height) {
            this.height = height;
            return this;
        }

        void show(final Stage stage) {
            try {
                Parent root = FXMLLoader.load(rootUrl);
                stage.setTitle(title);
                stage.setScene(new Scene(root, width, height));
                stage.show();
            } catch (IOException ex) {
                handleWindowCreatingException(ex);
            }
        }

        private void handleWindowCreatingException(final Exception ex) {
            // TODO
        }
    }
}
