package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Category;
import ru.unn.agile.PersonalFinance.Model.ExternalTransaction;
import ru.unn.agile.PersonalFinance.ViewModel.utils.GregorianCalendarHelper;
import ru.unn.agile.PersonalFinance.ViewModel.utils.StringHelper;

import java.time.LocalDate;
import java.util.Objects;

public class ExternalTransactionViewModel extends TransactionViewModel {
    private final AccountViewModel parentAccount;

    private ExternalTransaction modelExternalTransaction;
    private ExternalTransactionViewModelState savedState;

    private final StringProperty descriptionProperty = new SimpleStringProperty();

    private final StringProperty counterpartyProperty = new SimpleStringProperty();

    private final ObjectProperty<CategoryViewModel> categoryProperty =
            new SimpleObjectProperty<>();

    public ExternalTransactionViewModel(final AccountViewModel parentAccount) {
        Objects.requireNonNull(parentAccount);
        this.parentAccount = parentAccount;
        setUpBindings();
        setDefaults();
    }

    // region Properties for Binding

    public final String getDescription() {
        return this.descriptionProperty.get();
    }

    public final StringProperty descriptionProperty() {
        return this.descriptionProperty;
    }

    public final void setDescription(final String description) {
        this.descriptionProperty.set(description);
    }

    public final StringProperty counterpartyProperty() {
        return this.counterpartyProperty;
    }

    public final String getCounterparty() {
        return this.counterpartyProperty.get();
    }

    public final void setCounterparty(final String counterparty) {
        this.counterpartyProperty.set(counterparty);
    }

    public final ObjectProperty<CategoryViewModel> categoryProperty() {
        return this.categoryProperty;
    }

    public final CategoryViewModel getCategory() {
        return this.categoryProperty.get();
    }

    public final void setCategory(final CategoryViewModel category) {
        this.categoryProperty.set(category);
    }

    // endregion

    ExternalTransaction getModelExternalTransaction() {
        if (modelExternalTransaction == null) {
            throw new UnsupportedOperationException("Transaction should be "
                    + "saved before getting model transaction");
        }
        return modelExternalTransaction;
    }

    @Override
    protected void saveInternal() {
        if (!parentAccount.isSaved()) {
            throw new UnsupportedOperationException("Parent account should be "
                    + "saved before saving the transaction");
        }

        modelExternalTransaction = buildExternalTransaction();

        Account modelAccount = parentAccount.getModelAccount();
        modelAccount.addExternalTransaction(modelExternalTransaction);
        parentAccount.registerTransaction(this);
    }

    @Override
    protected void updateInternal() {
        Account modelAccount = parentAccount.getModelAccount();
        modelAccount.deleteTransaction(modelExternalTransaction);
        modelExternalTransaction = buildExternalTransaction();
        modelAccount.addExternalTransaction(modelExternalTransaction);
        parentAccount.forceUpdateBalance();
    }

    @Override
    protected void deleteInternal() {
        Account modelAccount = parentAccount.getModelAccount();
        modelAccount.deleteTransaction(modelExternalTransaction);
        parentAccount.unregisterTransaction(this);
    }

    @Override
    protected void saveState() {
        savedState = ExternalTransactionViewModelState.save(this);
    }

    @Override
    protected void recoverState() {
        savedState.recover(this);
    }

    private ExternalTransaction buildExternalTransaction() {
        ExternalTransaction.Builder transactionBuilder = getIsIncome()
                ? ExternalTransaction.incomeBuilder(getAmount())
                : ExternalTransaction.expenseBuilder(getAmount());

        return transactionBuilder
                .date(GregorianCalendarHelper.convertFromLocalDate(getDate()))
                .category(getModelCategory())
                .description(getDescription())
                .counterparty(getCounterparty())
                .build();
    }

    private Category getModelCategory() {
        CategoryViewModel categoryViewModel = getCategory();
        return categoryViewModel.getModelCategory();
    }

    private void setUpBindings() {
        BooleanBinding isCounterpartyEmptyBinding =
                StringHelper.isNullOrEmpty(counterpartyProperty);

        BooleanBinding isDescriptionEmptyBinding =
                StringHelper.isNullOrEmpty(descriptionProperty);

        BooleanBinding isAbleToSaveBinding =
                amountProperty().greaterThan(0)
                .and(isCounterpartyEmptyBinding.not())
                .and(isDescriptionEmptyBinding.not())
                .and(categoryProperty().isNotNull());

        isAbleToSaveMutableProperty().bind(isAbleToSaveBinding);

        displayCounterpartyMutableProperty().bind(counterpartyProperty);

        displayTitleMutableProperty().bind(descriptionProperty);
    }

    private void setDefaults() {
        setDate(LocalDate.now());
    }

}
