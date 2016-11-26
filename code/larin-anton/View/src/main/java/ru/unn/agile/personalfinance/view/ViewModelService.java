package ru.unn.agile.personalfinance.view;

import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;

final class ViewModelService {
    private static final Object LOCK = new Object();
    private static LedgerViewModel viewModel;

    static LedgerViewModel getViewModel() {
        // Using synchronized block instead of
        // synchronized method to prevent PMD violation
        synchronized (LOCK) {
            if (viewModel == null) {
                viewModel = new LedgerViewModel();
            }
        }
        return viewModel;
    }

    private ViewModelService() { }
}
