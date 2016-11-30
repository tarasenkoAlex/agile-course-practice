package main.java.view;

import javax.swing.*;

public class MortgageCalc {
    private JPanel mainPanel;
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

        frame.setContentPane(new MortgageCalc().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
