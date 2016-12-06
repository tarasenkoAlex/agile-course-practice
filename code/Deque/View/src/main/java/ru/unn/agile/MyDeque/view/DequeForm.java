package ru.unn.agile.MyDeque.view;

import javax.swing.*;

public class DequeForm {
    private JTextField pushField;
    private JTextField popField;
    private JButton pushEndButton;
    private JButton popEndButton;
    private JButton pushTopButton;
    private JButton popTopButton;
    private JLabel statusLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("DequeForm");
        frame.setContentPane(new DequeForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
}
