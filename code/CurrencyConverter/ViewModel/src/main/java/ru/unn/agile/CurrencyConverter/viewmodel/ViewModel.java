package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.CurrencyConverter.model.Constants;
import ru.unn.agile.CurrencyConverter.model.Converter;
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
    private final StringProperty logs = new SimpleStringProperty();
    private ILogger logger;

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    private void init() {
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

        amount.addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                message.set(getInputMessage().getValue());
                logger.log("Value changed to " + amount.get());
                updateLogs();
            }
        });

        fromCurrency.addListener(new CurrencyChangeListener());
        fromCurrency.addListener(new ChangeListener<Constants>() {
            @Override
            public void changed(final ObservableValue<? extends Constants> observable,
                                final Constants oldValue, final Constants newValue) {
                logger.log("FromCurrency changed to " + fromCurrency.get().toString());
                updateLogs();
            }
        });
        toCurrency.addListener(new CurrencyChangeListener());
        toCurrency.addListener(new ChangeListener<Constants>() {
            @Override
            public void changed(final ObservableValue<? extends Constants> observable,
                                final Constants oldValue, final Constants newValue) {
                logger.log("ToCurrency changed to " + toCurrency.get().toString());
                updateLogs();
            }
        });
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    private class CurrencyChangeListener implements ChangeListener<Constants> {

        @Override
        public void changed(final ObservableValue<? extends Constants> observable,
                            final Constants oldValue, final Constants newValue) {
            message.set(getInputMessage().getValue());
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
    public List<String> getLog() {
        return logger.getLog();
    }
    public StringProperty logsProperty() {
        return logs;
    }
    public String getLogs() {
        return logs.get();
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
        logger.log("Converted " + amount.get() + " "
                + fromCurrency.get().toString() + " to "
                + resultingValue.get() + " " + toCurrency.get().toString());
        updateLogs();
    }

    public MessageStatus getInputMessage() {
        MessageStatus inputMessage = MessageStatus.READY;
        if (amount.get().isEmpty()) {
            inputMessage = MessageStatus.WAITING;
        }
        try {
            if (Double.parseDouble(amount.get()) < 0) {
                inputMessage = MessageStatus.FAILED;
            }
        } catch (NumberFormatException ex) {
            inputMessage = MessageStatus.FAILED;
        }
        return inputMessage;
    }

    enum MessageStatus {
        SUCCESS("Success!"),
        FAILED("Error! Enter correct data"),
        WAITING("Please, enter input data"),
        READY("Please, press the Convert");

        private final String value;

        MessageStatus(final String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}
