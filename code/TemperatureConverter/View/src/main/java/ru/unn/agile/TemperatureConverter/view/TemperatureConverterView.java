package ru.unn.agile.TemperatureConverter.view;

import ru.unn.agile.TemperatureConverter.viewmodel.TemperatureConverterViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class TemperatureConverterView {
    private TemperatureConverterViewModel tempConverterViewModel;
    private JPanel mainPanel;
    private JTextField firstTextField;
    private JTextField secondTextField;
    private JComboBox firstComboBoxScales;
    private JComboBox secondComboBoxScales;
    private JLabel warningLabel;

    private TemperatureConverterView() {
    }

    private TemperatureConverterView(final TemperatureConverterViewModel tempConverterViewModel) {
        this.tempConverterViewModel = tempConverterViewModel;

        tempConverterViewModel.setFirstScale((String) firstComboBoxScales.getSelectedItem());
        tempConverterViewModel.setSecondScale((String) secondComboBoxScales.getSelectedItem());

        firstTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bindFirstValue();
                backBindSecondValue();
            }
        });

        secondTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bindSecondValue();
                backBindFirstValue();
            }
        });

        firstComboBoxScales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bindFirstScale();
                backBindFirstValue();
                backBindSecondValue();
            }
        });

        secondComboBoxScales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bindSecondScale();
                backBindFirstValue();
                backBindSecondValue();
            }
        });
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("TemperatureConverterView");
        TemperatureConverterViewModel tempConverterViewModel = new TemperatureConverterViewModel();
        frame.setContentPane(new TemperatureConverterView(tempConverterViewModel).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bindFirstValue() {
        tempConverterViewModel.setFirstValue(firstTextField.getText());
    }

    private void backBindSecondValue() {
        secondTextField.setText(tempConverterViewModel.getSecondValue());
        warningLabel.setText(tempConverterViewModel.getWarningLabelText());
    }

    private void bindSecondValue() {
        tempConverterViewModel.setSecondValue(secondTextField.getText());
    }

    private void backBindFirstValue() {
        firstTextField.setText(tempConverterViewModel.getFirstValue());
        warningLabel.setText(tempConverterViewModel.getWarningLabelText());
    }

    private void bindFirstScale() {
        tempConverterViewModel.setFirstScale((String) firstComboBoxScales.getSelectedItem());
    }

    private void bindSecondScale() {
        tempConverterViewModel.setSecondScale((String) secondComboBoxScales.getSelectedItem());
    }
}
