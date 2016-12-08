package ru.unn.agile.PersonalFinance.ViewModel;

import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Transfer;

import java.util.Objects;

class TransferViewModelSharedState {
    private boolean isDeleted;
    private final Transfer modelTransfer;
    private final Account associatedModelAccount;

    TransferViewModelSharedState(final Transfer modelTransfer,
                                 final Account associatedModelAccount) {
        Objects.requireNonNull(modelTransfer);
        Objects.requireNonNull(associatedModelAccount);
        this.associatedModelAccount = associatedModelAccount;
        this.modelTransfer = modelTransfer;
    }

    void delete() {
        if (!isDeleted) {
            associatedModelAccount.deleteTransaction(modelTransfer);
            isDeleted = true;
        }
    }

}
