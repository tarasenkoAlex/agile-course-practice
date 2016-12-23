package ru.unn.agile.MortgageCalculator.view;

import ru.unn.agile.MortgageCalculator.viewmodel.ViewModel;
import ru.unn.agile.MortgageCalculator.infrastructure.TextLogger;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public final class MortgageCalc {
    private JPanel mainPanel;
    private final ViewModel viewModel;
    private JButton calcButton;
    private JTextField deptTextField;
    private JTextField yearsTextField;
    private JTextField persentsTextField;
    private JTextField totalSumTextField;
    private JTextField paymentTextField;
    private JTextField overPaymentTextField;
    private JLabel statusLabel;
    private JList<String> lstLog;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Mortgage Calculator");
        TextLogger logger = new TextLogger("./TxtLogger-lab3.log");
        ViewModel viewModel = new ViewModel(logger);
        frame.setContentPane(new MortgageCalc(viewModel).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private MortgageCalc(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                MortgageCalc.this.viewModel.calculate();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                MortgageCalc.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        deptTextField.addKeyListener(keyListener);
        yearsTextField.addKeyListener(keyListener);
        persentsTextField.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bind();
                MortgageCalc.this.viewModel.focusLost();
                backBind();
            }
        };
        deptTextField.addFocusListener(focusLostListener);
        yearsTextField.addFocusListener(focusLostListener);
        persentsTextField.addFocusListener(focusLostListener);
    }

    private void bind() {
        viewModel.setDebt(deptTextField.getText());
        viewModel.setYears(yearsTextField.getText());
        viewModel.setPercents(persentsTextField.getText());
    }

    private void backBind() {
        calcButton.setEnabled(viewModel.isCalcButtonEnabled());

        totalSumTextField.setText(viewModel.getTotalSum());
        paymentTextField.setText(viewModel.getPayment());
        overPaymentTextField.setText(viewModel.getOverPayment());

        statusLabel.setText(viewModel.getStatus());

        List<String> log = viewModel.getLog();
        String[] logsArray = log.toArray(new String[log.size()]);
        lstLog.setListData(logsArray);
    }
}
