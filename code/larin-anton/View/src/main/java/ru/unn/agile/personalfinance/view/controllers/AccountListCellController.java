package ru.unn.agile.personalfinance.view.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;

public class AccountListCellController extends DataContextController<AccountViewModel> {
    @FXML
    private Label nameLabel;

    @FXML
    private Label balanceLabel;

    @Override
    protected void addBindings(final AccountViewModel account) {
        /* account.name -> nameLabel.text */
        nameLabel.textProperty().bind(account.nameProperty());

        /* balanceLabel.text <-> account.balance */
        Bindings.bindBidirectional(
                balanceLabel.textProperty(),
                account.balanceProperty(),
                new CurrencyStringConverter());
    }

    @Override
    protected void removeBindings(final AccountViewModel account) {
        nameLabel.textProperty().unbind();

        Bindings.unbindBidirectional(
                balanceLabel.textProperty(),
                account.balanceProperty());
    }
}
