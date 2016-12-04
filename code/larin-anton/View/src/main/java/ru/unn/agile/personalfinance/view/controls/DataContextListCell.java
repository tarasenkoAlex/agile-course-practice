package ru.unn.agile.personalfinance.view.controls;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ru.unn.agile.personalfinance.view.controllers.DataContextController;

import java.io.IOException;
import java.net.URL;

public class DataContextListCell<T> extends JFXListCell<T> {
    private final URL fxmlSource;
    private FXMLLoader fxmlLoader;

    public DataContextListCell(final String fxmlSourceName) {
        fxmlSource = getClass().getResource(fxmlSourceName);
    }

    @Override
    public void updateItem(final T item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = getLoader(item);

            Node root = loader.getRoot();
            root.setMouseTransparent(true);

            setText(null);
            setGraphic(root);

            DataContextController controller = loader.getController();
            controller.setDataContext(item);
        }
    }

    private FXMLLoader getLoader(final T item) {
        if (fxmlLoader != null) {
            return fxmlLoader;
        }

        fxmlLoader = new FXMLLoader(fxmlSource);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException("We can't find template for object of type "
                    + item.getClass().getName());
        }
        return fxmlLoader;
    }
}
