package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.CurrencyConverter.model.Constants;
import ru.unn.agile.CurrencyConverter.model.Converter;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty amount = new SimpleStringProperty();
    private final ObjectProperty<Constants> fromCurrency = new SimpleObjectProperty<>();
    private final ObjectProperty<Constants> toCurrency = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<Constants>> currencies =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Constants.values()));

    private final StringProperty resultingValue = new SimpleStringProperty();
    private final StringProperty message = new SimpleStringProperty();

    private final BooleanProperty convertionDisabled = new SimpleBooleanProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        amount.set("");
        fromCurrency.set(Constants.RUBLE);
        toCurrency.set(Constants.DOLLAR);
        resultingValue.set("");
        message.set(MessageStatus.WAITING.getValue());

        BooleanBinding couldConvert = new BooleanBinding() {
            {
                super.bind(amount);
            }
            @Override
            protected boolean computeValue() {
                return getInputMessage() == MessageStatus.READY;
            }
        };
        convertionDisabled.bind(couldConvert.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(amount);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public ObjectProperty<Constants> fromCurrencyProperty() {
        return fromCurrency;
    }

    public ObjectProperty<Constants> toCurrencyProperty() {
        return toCurrency;
    }

    public String getResultingValue() {
        return resultingValue.get();
    }
    public StringProperty resultingValueProperty() {
        return resultingValue;
    }

    public String getMessage() {
        return message.get();
    }
    public StringProperty messageProperty() {
        return message;
    }

    public BooleanProperty convertionDisabledProperty() {
        return convertionDisabled;
    }
    public final boolean getConvertionDisabled() {
        return convertionDisabled.get();
    }

    public ObjectProperty<ObservableList<Constants>> currenciesProperty() {
        return currencies;
    }
    public final ObservableList<Constants> getCurrencies() {
        return currencies.get();
    }

    public void convert() {
        if (convertionDisabled.get()) {
            return;
        }
        Converter converter = new Converter();

        double amountValue = Double.parseDouble(amount.get());
        double fromCurr = fromCurrency.get().getValue();
        double toCurr = toCurrency.get().getValue();

        String result = String.valueOf(converter.execute(amountValue, fromCurr, toCurr));
        resultingValue.set(result);
        message.set(MessageStatus.SUCCESS.getValue());
    }

    public MessageStatus getInputMessage() {
        MessageStatus inputMessage = MessageStatus.READY;
        if (amount.get().isEmpty()) {
            inputMessage = MessageStatus.WAITING;
        }
        try {
            if (!amount.get().isEmpty()) {
                Double.parseDouble(amount.get());
            }
            if (!amount.get().isEmpty() && Double.parseDouble(amount.get()) < 0) {
                inputMessage = MessageStatus.FAILED;
            }
        } catch (NumberFormatException ex) {
            inputMessage = MessageStatus.FAILED;
        }
        return inputMessage;
    }
    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            message.set(getInputMessage().getValue());
        }
    }

    enum MessageStatus {
        SUCCESS("Success!"),
        FAILED("Error! Enter correct data to field"),
        WAITING("Please, enter input data"),
        READY("Please, press the Convert button");

        private final String value;

        MessageStatus(final String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}
