package ru.unn.agile.personalfinance.view.controls;

import javafx.scene.control.ListCell;

import java.util.Objects;
import java.util.function.Function;

public class StringListCell<T> extends ListCell<T> {
    private final Function<T, String> toStringConverter;

    public StringListCell(final Function<T, String> converter) {
        Objects.requireNonNull(converter);
        toStringConverter = converter;
    }

    @Override
    protected void updateItem(final T item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            setText(toStringConverter.apply(item));
        }
    }
}
