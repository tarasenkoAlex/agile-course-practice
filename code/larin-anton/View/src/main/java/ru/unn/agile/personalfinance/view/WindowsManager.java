package ru.unn.agile.personalfinance.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.ExternalTransactionViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransferViewModel;
import ru.unn.agile.personalfinance.view.controllers.DataContextController;

import java.net.URL;
import java.util.HashMap;
import java.util.Stack;

public final class WindowsManager {
    private static final int TRANSACTIONS_WINDOW_WIDTH = 700;
    private static final int TRANSACTIONS_WINDOW_HEIGHT = 400;
    private static final int ADD_ACCOUNT_WINDOW_WIDTH = 400;
    private static final int ADD_ACCOUNT_WINDOW_HEIGHT = 200;
    private static final int ADD_EXTERNAL_TRANSACTION_WINDOW_WIDTH = 500;
    private static final int ADD_EXTERNAL_TRANSACTION_WINDOW_HEIGHT = 350;
    private static final int ADD_TRANSFER_WINDOW_WIDTH = 500;
    private static final int ADD_TRANSFER_WINDOW_HEIGHT = 250;


    private static final Object LOCK = new Object();
    private static WindowsManager windowsManager;

    private final Stack<Stage> stagesHistory = new Stack<>();
    private final HashMap<URL, Pair<Scene, DataContextController>> scenesCache = new HashMap<>();

    private WindowsManager() {
    }

    public static WindowsManager getInstance() {
        // Using synchronized block instead of
        // synchronized method to prevent PMD violation
        synchronized (LOCK) {
            if (windowsManager == null) {
                windowsManager = new WindowsManager();
            }
        }
        return windowsManager;
    }

    public void showHomeScreenView(final Stage rootStage) {
        getView("home-screen.fxml")
                .title("My Wallet")
                .width(TRANSACTIONS_WINDOW_WIDTH)
                .height(TRANSACTIONS_WINDOW_HEIGHT)
                .userStage(rootStage)
                .show();
    }

    public void showEditAccountView(final AccountViewModel account) {
        getView("edit-account.fxml")
                .title("Add new account")
                .width(ADD_ACCOUNT_WINDOW_WIDTH)
                .height(ADD_ACCOUNT_WINDOW_HEIGHT)
                .data(account)
                .show();
    }

    public void showEditExternalTransactionView(final ExternalTransactionViewModel transaction) {
        getView("edit-external-transaction.fxml")
                .title("Add new transaction")
                .width(ADD_EXTERNAL_TRANSACTION_WINDOW_WIDTH)
                .height(ADD_EXTERNAL_TRANSACTION_WINDOW_HEIGHT)
                .data(transaction)
                .show();
    }

    public void showAddTransferView(final TransferViewModel transfer) {
        getView("edit-transfer.fxml")
                .title("Add new transfer")
                .width(ADD_TRANSFER_WINDOW_WIDTH)
                .height(ADD_TRANSFER_WINDOW_HEIGHT)
                .data(transfer)
                .show();
    }

    public void goBack() {
        Stage currentStage = stagesHistory.pop();
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
        private Stage userStage;
        private Object dataObject;

        ViewBuilder(final String fxmlSource) {
            rootUrl = getClass().getResource(fxmlSource);
        }

        ViewBuilder title(final String title) {
            this.title = title;
            return this;
        }

        ViewBuilder width(final int width) {
            this.width = width;
            return this;
        }

        ViewBuilder height(final int height) {
            this.height = height;
            return this;
        }

        ViewBuilder userStage(final Stage userStage) {
            this.userStage = userStage;
            return this;
        }

        ViewBuilder data(final Object dataObject) {
            this.dataObject = dataObject;
            return this;
        }

        void show() {
            if (scenesCache.containsKey(rootUrl)) {
                Pair<Scene, DataContextController> sceneAndController = scenesCache.get(rootUrl);
                showScene(sceneAndController.getKey(), sceneAndController.getValue());
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(rootUrl);
                    loader.load();
                    Parent root = loader.getRoot();
                    DataContextController controller = loader.getController();

                    Scene scene = new Scene(root);
                    scenesCache.put(rootUrl, new Pair<>(scene, controller));
                    showScene(scene, controller);
                } catch (Exception ex) {
                    handleWindowCreatingException(ex);
                }
            }
        }

        private void showScene(final Scene scene, final DataContextController controller) {
            Stage stage = getStage();

            if (!stagesHistory.isEmpty()) {
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(stagesHistory.peek());
            }
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setWidth(width);
            stage.setHeight(height);

            stagesHistory.push(stage);

            stage.setOnHidden(event -> {
                controller.setDataContext(null);
            });
            stage.show();
            controller.setDataContext(dataObject);
        }

        private Stage getStage() {
            return (userStage == null) ? new Stage() : userStage;
        }

        private void handleWindowCreatingException(final Exception ex) {
            ex.printStackTrace();
        }

    }
}
