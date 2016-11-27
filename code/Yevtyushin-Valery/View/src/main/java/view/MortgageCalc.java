package main.java.view;

import javax.swing.*;

/**
 * Created by maxop on 11/22/16.
 */
public class MortgageCalc {
    private JPanel mainPanel;
    private JButton calcButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel statusLabel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");

        frame.setContentPane(new MortgageCalc().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
