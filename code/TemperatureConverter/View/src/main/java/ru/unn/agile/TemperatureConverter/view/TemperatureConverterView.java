package ru.unn.agile.TemperatureConverter.view;

import javax.swing.*;

/**
 * Created by nyuku on 01.12.2016.
 */
public class TemperatureConverterView {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("TemperatureConverterView");
        frame.setContentPane(new TemperatureConverterView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
