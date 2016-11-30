package main.java.view;

import main.java.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");

        frame.setContentPane(new MortgageCalc(new ViewModel()).mainPanel);
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
    }

}
