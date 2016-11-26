package ru.unn.agile.personalfinance.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;

public class ViewModelSource {
    private final ObjectProperty<LedgerViewModel> model;

    public ViewModelSource() {
        LedgerViewModel viewModel = ViewModelService.getViewModel();
        this.model = new SimpleObjectProperty<>(viewModel);
    }

    public LedgerViewModel getModel() {
        return this.model.get();
    }
}
